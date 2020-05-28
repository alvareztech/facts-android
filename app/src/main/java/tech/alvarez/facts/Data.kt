package tech.alvarez.facts

import tech.alvarez.facts.util.Util

data class Info(val label: String, val value: String)

val deviceInfo = listOf(
    Info("Security Path", Util.securityPath),
    Info("Device Name", Util.getDeviceName()),
    Info("Manufacturer", Util.manufacturer),
    Info("Product", Util.product),
    Info("Model", Util.model),
    Info("Screen Size", Util.screenSize()),
    Info("Board", Util.board),
    Info("Display", Util.display),
    Info("Hardware", Util.hardware),
    Info("32 bit ABIs supported", Util.supported32bits()),
    Info("64 bit ABIs supported", Util.supported64bits()),
    Info("Total", Util.totalMemory())
)

val osInfo = listOf(
    Info("Android Version", Util.androidVersion()),
    Info("Android Release", Util.androidRelease())
)

val gmsInfo = listOf(
    Info("Google Play Services", Util.googlePlayServices()),
    Info("Google Play Store Package", Util.googlePlayStorePackage),
    Info(
        "Google Play Services available",
        if (Util.isGooglePlayServicesAvailable()) "Yes" else "No"
    )
)
val hmsInfo = listOf(
    Info(
        "Huawei Mobile Services available",
        if (Util.isHuaweiMobileServicesAvailable()) "Yes" else "No"
    )
)

val featureInfo = listOf(
    Info(
        "Huawei Mobile Services available",
        if (Util.isHuaweiMobileServicesAvailable()) "Yes" else "No"
    )
)