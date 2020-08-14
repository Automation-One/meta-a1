SUMMARY = "Automation One NAND installer"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "\
    file://${BPN} \
    file://${BPN}.service \
"

inherit systemd

SYSTEMD_SERVICE_${PN} = "${PN}.service"

do_install() {
    install -Dm 0755 ${WORKDIR}/${PN} ${D}/${bindir}/${PN}
    install -Dm 0644 ${WORKDIR}/${PN}.service ${D}${systemd_unitdir}/system/${PN}.service
    sed -i -e 's,@SPL_BINARY@,${SPL_BINARY},g' \
        -e 's,@UBOOT_BINARY@,${UBOOT_BINARY},g' \
        ${D}/${bindir}/${PN}
}

RDEPENDS_${PN} += "\
    curl \
    mtd-utils \
    mtd-utils-ubifs \
"

COMPATIBLE_MACHINE = "imx6ull-a1-r1"
