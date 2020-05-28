package tech.alvarez.facts

import tech.alvarez.facts.util.*

data class Info(val label: String, val value: String)

fun Boolean.literal(): String {
    return if (this) "Yes" else "No"
}

const val notAvailable = "Not Available"

val deviceInfo = listOf(
    Info("Device Name", Feature.getDeviceName()),
    Info("Manufacturer", Device.manufacturer()),
    Info("Product", Device.product()),
    Info("Model", Device.model()),
    Info("Screen Size", Feature.screenSize()),
    Info("32 bit ABIs supported", Device.supported32bits()),
    Info("64 bit ABIs supported", Device.supported64bits()),
    Info("Java VM", Device.javaVM()),
    Info("Density", Device.density()),
    Info("Total Memory", Device.totalMemory())
)

val osInfo = listOf(
    Info("Version Release", OS.versionRelease()),
    Info("SDK", OS.sdk()),
    Info("Base OS", OS.baseOS()),
    Info("Version Codename", OS.versionCodename()),
    Info("Security Path", OS.securityPath()),
    Info("Board", Device.board()),
    Info("Display", Device.display()),
    Info("Hardware", Device.hardware())
)

val gmsInfo = listOf(
    Info("Google Play Services Available", GMS.isGMSAvailable().literal()),
    Info("Google Play Services Version Code", GMS.playServicesVersionCode()),
    Info("Google Play Store Package", GMS.playStorePackage()),
    Info("Google Play Services Package", GMS.playServicesPackage())
)
val hmsInfo = listOf(
    Info("Huawei Mobile Services available", HMS.isHMSAvailable().literal()),
    Info("Version Code", HMS.versionCode()),
    Info("Action", HMS.action()),
    Info("Activity Name", HMS.activityName()),
    Info("Version Code ID", HMS.versionCodeId()),
    Info("JSON Version Min", HMS.jsonVersionMin()),
    Info("SDK Version Name", HMS.sdkVersionName()),
    Info("SDK Version Code", HMS.sdkVersionCode()),
    Info("App ID", HMS.appId())
)

val featureInfo = listOf(
    Info("Bluetooth", Feature.hasBluetooth().literal()),
    Info("Bluetooth LE", Feature.hasBluetoothLE().literal())
)