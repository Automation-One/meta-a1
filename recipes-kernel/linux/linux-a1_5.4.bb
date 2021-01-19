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

LINUX_VERSION = "5.4.72"
SRCBRANCH = "5.4.x+a1"
SRCREV = "45562b01332739169570a15b875909b497f6c2ff"

COMPATIBLE_MACHINE = "imx6ull-a1-r1"
