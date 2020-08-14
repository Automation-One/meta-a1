do_rootfs[depends] += "a1-image-base:do_image_complete"

A1_INSTALLER_IMAGE_ROOTFS = "a1-image-base-${MACHINE}"

require a1-image-installer-nand.inc

copy_a1_image_files() {
    local dest_dirname=installer-data
    local dest=${IMAGE_ROOTFS}/$dest_dirname
    mkdir -p $dest

    cp ${DEPLOY_DIR_IMAGE}/${A1_INSTALLER_IMAGE_ROOTFS}.ubi $dest/rootfs.ubi
}
