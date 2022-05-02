
SUMMARY = "Automation One boot.scr"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "\
    file://boot.cmd \
"

DEPENDS = "u-boot-mkimage-native"

inherit deploy

do_generate_bootscript() {
    cp ${WORKDIR}/boot.cmd ${B}/boot.cmd
}
do_generate_bootscript[dirs] = "${B}"

addtask generate_bootscript after do_configure before do_mkimage

do_mkimage() {
    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "boot script" -d ${B}/boot.cmd ${B}/boot.scr
}
do_mkimage[dirs] = "${B}"

addtask mkimage after do_generate_bootscript before do_install






do_install() {
    install -Dm 0644 ${B}/boot.scr ${D}/boot.scr
}

do_deploy() {
    install -Dm 0644 ${D}/boot.scr ${DEPLOYDIR}/boot.scr-${MACHINE}-${PV}-${PR}
    cd ${DEPLOYDIR}
    rm -f boot.scr-${MACHINE} boot.scr
    ln -sf boot.scr-${MACHINE}-${PV}-${PR} boot.scr-${MACHINE}
    ln -sf boot.scr-${MACHINE}-${PV}-${PR} boot.scr
}

addtask deploy after do_install before do_build




FILES_${PN} += "/"

PROVIDES += "u-boot-default-script"
RPROVIDES_${PN} += "u-boot-default-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"
