# Copyright (C) 2012-2020 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-bsp/u-boot/u-boot.inc

SUMMARY = "U-Boot for Automation One based boards"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bison-native bc-native dtc-native lzop-native"

SRC_URI = "git://github.com/Automation-One/${BPN}.git;protocol=https;branch=${SRCBRANCH} \
           file://fw_env.config \
           file://0001-Added-crc-check-to-mmc_load_legacy-in-order-to-avoid.patch \
           "

SRCREV = "92959a3f4f27ac275b9fe75f66579001de13b3fd"
SRCBRANCH = "2020.01+a1"
LOCALVERSION = "-a1"

PROVIDES += "u-boot"

PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit fsl-u-boot-localversion

# FIXME: Allow linking of 'tools' binaries with native libraries
#        used for generating the boot logo and other tools used
#        during the build process.
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CPPFLAGS}" \
                 HOSTLDFLAGS="${BUILD_LDFLAGS}" \
                 HOSTSTRIP=true'

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "imx6ull-a1-r1"
