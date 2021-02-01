# Copyright (C) 2012-2020 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)
#
# SPDX-License-Identifier: MIT
#

require recipes-kernel/linux/linux-imx.inc

SUMMARY = "Kontron Linux mainline based kernel with backported features and fixes"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native"

SRC_URI = "\
    git://github.com/Automation-One/${BPN}.git;protocol=https;branch=${SRCBRANCH} \
"

LOCALVERSION = "-a1"

LINUX_VERSION = "5.4.85"
SRCBRANCH = "5.4.x+a1"
SRCREV = "7c4a7aa196418c63331e658e21b5939f7467a818"

COMPATIBLE_MACHINE = "imx6ull-a1-r1"
