SUMMARY = "Automation One Image Base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

EXTRA_IMAGE_FEATURES += "ssh-server-openssh"

inherit core-image image_types_512m-ubifs-nand

IMAGE_FSTYPES_imx6ull-a1-r1 = "512m-ubifs-nand"
