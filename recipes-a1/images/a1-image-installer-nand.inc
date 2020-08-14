DESCRIPTION = "Automation One Installer Image to NAND device"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

# Avoid installation of syslog
BAD_RECOMMENDATIONS += "busybox-syslog"

# Avoid static /dev
USE_DEVFS = "1"

inherit core-image

CORE_IMAGE_EXTRA_INSTALL = "\
    a1-installer \
    u-boot-installer-nand-script \
"

remove_fstab() {
   rm -f ${IMAGE_ROOTFS}/${sysconfdir}/fstab
}

copy_uboot_file() {
    local dest_dirname=installer-data
    local dest=${IMAGE_ROOTFS}/$dest_dirname
    mkdir -p $dest

    cp ${DEPLOY_DIR_IMAGE}/${SPL_BINARY} $dest/${SPL_BINARY}
    cp ${DEPLOY_DIR_IMAGE}/${UBOOT_BINARY} $dest/${UBOOT_BINARY}
}

ROOTFS_POSTPROCESS_COMMAND = "remove_fstab; copy_uboot_file; copy_a1_image_files; "
