PACKAGES =+ "${PN}-rs9113 ${PN}-rs9116"

FILES_${PN}-rs9113 = "\
    ${nonarch_base_libdir}/firmware/rsi_91x.fw \
    ${nonarch_base_libdir}/firmware/rsi/rs9113_ap_bt_dual_mode.rps \
    ${nonarch_base_libdir}/firmware/rsi/rs9113_wlan_bt_dual_mode.rps \
    ${nonarch_base_libdir}/firmware/rsi/rs9113_wlan_qspi.rps \
"

FILES_${PN}-rs9116 = "\
    ${nonarch_base_libdir}/firmware/rsi_91x.fw \
    ${nonarch_base_libdir}/firmware/rsi/rs9116_wlan_bt_classic.rps \
    ${nonarch_base_libdir}/firmware/rsi/rs9116_wlan.rps \
"
