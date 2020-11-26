FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

OSSYSTEMS_FACTORY_DEFAULTS_HOOKS = "file://${BPN}.factory-defaults-hook"

inherit ossystems-factory-defaults

PACKAGECONFIG_append = " modemmanager"

# The /var/lib/NetworkManager and /etc/NetworkManager/system-connections need to
# be a link to /data partition to be persistent across update. To do this is
# necessary remove the directory from image and create a link to /data. The
# /data directory structure is created by factory-defaults-hook.
do_install_append() {
    rm -rf ${D}${localstatedir}/lib/NetworkManager
    ln -sf /data${localstatedir}/lib/NetworkManager ${D}${localstatedir}/lib/NetworkManager
    rm -rf ${D}${sysconfdir}/NetworkManager/system-connections
    ln -sf /data${sysconfdir}/NetworkManager/system-connections ${D}${sysconfdir}/NetworkManager/
}
