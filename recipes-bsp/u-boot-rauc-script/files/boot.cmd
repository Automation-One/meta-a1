test -n "${BOOT_ORDER}" || setenv BOOT_ORDER "A B"
test -n "${BOOT_A_LEFT}" || setenv BOOT_A_LEFT 3
test -n "${BOOT_B_LEFT}" || setenv BOOT_B_LEFT 3
test -n "${additional_boot_args}" || setenv additional_boot_args "quiet"


 

setenv find_root_a "ubi part spi-nand0; ubifsmount ubi0:system_a; setenv ubiroot ubi0:system_a"
setenv find_root_b "ubi part spi-nand0; ubifsmount ubi0:system_b; setenv ubiroot ubi0:system_b"

setenv bootargs
for BOOT_SLOT in "${BOOT_ORDER}"; do
  if test "x${bootargs}" != "x"; then
    # skip remaining slots
  elif test "x${BOOT_SLOT}" = "xA"; then
    if test ${BOOT_A_LEFT} -gt 0; then
      echo "Found valid slot A, ${BOOT_A_LEFT} attempts remaining"
      setexpr BOOT_A_LEFT ${BOOT_A_LEFT} - 1
      run find_root_a
      setenv bootargs "console=ttymxc3,115200 ubi.mtd=0 root=${ubiroot} rootfstype=ubifs rw imx2-wdt.timeout=60 imx2-wdt.nowayout panic=60 rauc.slot=A ${additional_boot_args}"
    fi
  elif test "x${BOOT_SLOT}" = "xB"; then
    if test ${BOOT_B_LEFT} -gt 0; then
      echo "Found valid slot B, ${BOOT_B_LEFT} attempts remaining"
      setexpr BOOT_B_LEFT ${BOOT_B_LEFT} - 1
      run find_root_b
      setenv bootargs "console=ttymxc3,115200 ubi.mtd=0 root=${ubiroot} rootfstype=ubifs rw imx2-wdt.timeout=60 imx2-wdt.nowayout panic=60 rauc.slot=B ${additional_boot_args}"
    fi
  fi
done





if test -n "${bootargs}"; then
  saveenv
else
  echo "No valid slot found, resetting tries to 3"
  setenv BOOT_A_LEFT 3
  setenv BOOT_B_LEFT 3
  saveenv
  reset
fi


echo "Loading kernel"
ubifsload ${loadaddr} /boot/fitImage
imxtract ${loadaddr} fdt-imx6ull-a1-gw.dtb ${fdt_addr_r}
imxtract ${loadaddr} kernel-1 ${kernel_addr_r}
echo " Starting kernel"
bootz ${kernel_addr_r} - ${fdt_addr_r}