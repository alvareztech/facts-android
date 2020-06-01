package tech.alvarez.facts

import android.Manifest
import tech.alvarez.facts.util.*

data class Info(val label: String, val value: String, val permission: String?)

fun Boolean.literal(): String {
    return if (this) "Yes" else "No"
}

class Message {
    companion object {
        const val notAvailable = "Not Available"
        const val requiresPermission = "Requires Permission (tap here)"
    }
}

val deviceInfo = listOf(
    Info("Device Name", Feature.getDeviceName(), null),
    Info("Manufacturer", Device.manufacturer(), null),
    Info("Product", Device.product(), null),
    Info("Model", Device.model(), null),
    Info("IMEI", Device.imei(), Manifest.permission.READ_PHONE_STATE),
    Info("Screen Size", Feature.screenSize(), null),
    Info("32 bit ABIs supported", Device.supported32bits(), null),
    Info("64 bit ABIs supported", Device.supported64bits(), null),
    Info("Java VM", Device.javaVM(), null),
    Info("Density", Device.density(), null),
    Info("Total Memory", Device.totalMemory(), null)
)

val osInfo = listOf(
    Info("Version Release", OS.versionRelease(), null),
    Info("SDK", OS.sdk(), null),
    Info("Base OS", OS.baseOS(), null),
    Info("Version Codename", OS.versionCodename(), null),
    Info("Security Path", OS.securityPath(), null),
    Info("Board", Device.board(), null),
    Info("Display", Device.display(), null),
    Info("Hardware", Device.hardware(), null)
)

val gmsInfo = listOf(
    Info("Google Play Services Available", GMS.isGMSAvailable().literal(), null),
    Info("Google Play Services Version Code", GMS.playServicesVersionCode(), null),
    Info("Google Play Store Package", GMS.playStorePackage(), null),
    Info("Google Play Services Package", GMS.playServicesPackage(), null)
)
val hmsInfo = listOf(
    Info("Huawei Mobile Services available", HMS.isHMSAvailable().literal(), null),
    Info("Version Code", HMS.versionCode(), null),
    Info("Action", HMS.action(), null),
    Info("Activity Name", HMS.activityName(), null),
    Info("Version Code ID", HMS.versionCodeId(), null),
    Info("JSON Version Min", HMS.jsonVersionMin(), null),
    Info("SDK Version Name", HMS.sdkVersionName(), null),
    Info("SDK Version Code", HMS.sdkVersionCode(), null),
    Info("App ID", HMS.appId(), null)
)

val featureInfo = listOf(
    Info("Bluetooth", Feature.hasBluetooth().literal(), null),
    Info("Bluetooth LE", Feature.hasBluetoothLE().literal(), null)
)