inherit image_types

do_image_512m_ubifs_nand[depends] += "virtual/bootloader:do_deploy u-boot-default-script:do_deploy"
IMAGE_TYPEDEP_512m-ubifs-nand_append = " ubifs"

IMAGE_CMD_512m-ubifs-nand() {
    cat > ${WORKDIR}/ubimg-${IMAGE_NAME}.cfg <<EOF
[boot]
mode=ubi
image=${WORKDIR}/boot.ubifs
vol_id=0
vol_size=10MiB
vol_type=dynamic
vol_name=boot
vol_flags=autoresize

[system_a]
mode=ubi
image=${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.ubifs
vol_id=1
vol_size=235MiB
vol_type=dynamic
vol_name=system_a

[system_b]
mode=ubi
image=${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.ubifs
vol_id=2
vol_size=235MiB
vol_type=dynamic
vol_name=system_b

[data]
mode=ubi
image=${WORKDIR}/data.ubifs
vol_id=3
vol_size=10MiB
vol_type=dynamic
vol_name=data
EOF

    rm -rf "${WORKDIR}/data" || true
    mkdir -p "${WORKDIR}/data"

    # Create data UBIFS image
    mkfs.ubifs -o "${WORKDIR}/data.ubifs" -r "${WORKDIR}/data" ${MKUBIFS_ARGS_INTERNAL}

    rm -rf "${WORKDIR}/boot" || true
    mkdir -p "${WORKDIR}/boot"
    cp -L ${DEPLOY_DIR_IMAGE}/boot.scr-${MACHINE} ${WORKDIR}/boot/boot.scr

    # Create boot UBIFS image
    mkfs.ubifs -o "${WORKDIR}/boot.ubifs" -r "${WORKDIR}/boot" ${MKUBIFS_ARGS_INTERNAL}

    ubinize -o ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ubi ${UBINIZE_ARGS} ${WORKDIR}/ubimg-${IMAGE_NAME}.cfg
    ln -sf ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ubi ${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.ubi
}
