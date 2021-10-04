PACKAGECONFIG_append = " modemmanager" 

do_install_append() {
    mkdir -p ${D}${localstatedir}/lib/NetworkManager
    mkdir -p ${D}${sysconfdir}/NetworkManager/system-connections
}
