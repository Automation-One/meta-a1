DESCRIPTION = "Boot script to be used with Automation One installer image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DEPENDS = "u-boot-mkimage-native"

inherit deploy

do_generate_bootscript() {
    cat > ${B}/boot.cmd <<EOF

setenv bootargs ''

load mmc 0:2 \${loadaddr} /boot/fitImage;
imxtract \${loadaddr} fdt@imx6ull-a1-gw.dtb \${fdt_addr_r};
imxtract \${loadaddr} kernel@1 \${kernel_addr_r};

setenv bootargs console=ttymxc3,115200 root=/dev/mmcblk0p2 rootwait rw quiet

bootz \${kernel_addr_r} - \${fdt_addr_r}

EOF
}
do_generate_bootscript[dirs] = "${B}"

addtask generate_bootscript after do_configure before do_mkimage

do_mkimage() {
    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "boot-installer-nand.script" -d ${B}/boot.cmd ${B}/boot-installer-nand.scr
}
do_mkimage[dirs] = "${B}"

addtask mkimage after do_generate_bootscript before do_install

do_install() {
    install -Dm 0644 ${B}/boot-installer-nand.scr ${D}/boot-installer-nand.scr
}

do_deploy() {
    install -Dm 0644 ${D}/boot-installer-nand.scr ${DEPLOYDIR}/boot-installer-nand.scr-${MACHINE}-${PV}-${PR}
    cd ${DEPLOYDIR}
    rm -f boot-installer-nand.scr-${MACHINE}
    ln -sf boot-installer-nand.scr-${MACHINE}-${PV}-${PR} boot-installer-nand.scr-${MACHINE}
}

addtask deploy after do_install before do_build

FILES_${PN} += "/"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "imx6ull-a1-r1"
