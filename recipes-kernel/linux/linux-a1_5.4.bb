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
    file://defconfig \
"

LOCALVERSION = "-a1"

LINUX_VERSION = "5.4.53"
SRCBRANCH = "5.4.x+a1"
SRCREV = "48190db773aa1d11ead92088329d615bcc08ad8d"

COMPATIBLE_MACHINE = "imx6ull-a1-r1"