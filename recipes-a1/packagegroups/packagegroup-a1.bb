SUMMARY = "Automation-One Package Group"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "\
    packagegroup-a1-networking \
"

RDEPENDS_packagegroup-a1-networking = "\
    modemmanager \
    networkmanager \
"
