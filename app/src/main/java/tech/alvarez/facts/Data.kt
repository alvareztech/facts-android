package tech.alvarez.facts

import android.Manifest
import tech.alvarez.facts.util.*

data class Info(val label: String, val value: String, val icon: Int, val permission: String?)

fun Boolean.literal(): String {
    return if (this) "Yes" else "No"
}

class Message {
    companion object {
        const val notAvailable = "Not Available"
        const val requiresPermission = "Requires Permission (tap here)"
        const val none = "None"
    }
}

fun deviceInfo(): List<Info> {
    return listOf(
        Info("Device Name", Feature.getDeviceName(), 0, null),
        Info("Manufacturer", Device.manufacturer(), 0, null),
        Info("Product", Device.product(), 0, null),
        Info("Model", Device.model(), 0, null),
        Info("IMEI", Device.imei(), 0, Manifest.permission.READ_PHONE_STATE),
        Info("Screen Size", Feature.screenSize(), 0, null),
        Info("32 bit ABIs supported", Device.supported32bits(), 0, null),
        Info("64 bit ABIs supported", Device.supported64bits(), 0, null),
        Info("Java VM", Device.javaVM(), 0, null),
        Info("Density", Device.density(), 0, null),
        Info("Total Memory", Device.totalMemory(), 0, null)
    )
}

val osInfo = listOf(
    Info("Version Release", OS.versionRelease(), 0, null),
    Info("SDK", OS.sdk(), 0, null),
    Info("Base OS", OS.baseOS(), 0, null),
    Info("Version Codename", OS.versionCodename(), 0, null),
    Info("Security Path", OS.securityPath(), 0, null),
    Info("Board", Device.board(), 0, null),
    Info("Display", Device.display(), 0, null),
    Info("Hardware", Device.hardware(), 0, null)
)

val gmsInfo = listOf(
    Info("Google Play Services Available", GMS.isGMSAvailable().literal(), 0, null),
    Info("Google Play Services Version", GMS.playServicesVersion(), 0, null),
    Info("Google Play Store Version", GMS.playStoreVersion(), 0, null),
    Info("Google Play Services Version Code", GMS.playServicesVersionCode(), 0, null),
    Info("Google Play Store Package", GMS.playStorePackage(), 0, null),
    Info("Google Play Services Package", GMS.playServicesPackage(), 0, null)
)
val hmsInfo = listOf(
    Info("Huawei Mobile Services available", HMS.isHMSAvailable().literal(), 0, null),
    Info("Huawei Mobile Services Version", HMS.hmsVersion(), 0, null),
    Info("Huawei AppGallery Version", HMS.appGalleryVersion(), 0, null),
    Info("SDK Version", HMS.sdkVersion(), 0, null),
    Info("Version Code", HMS.versionCode(), 0, null),
    Info("Action", HMS.action(), 0, null),
    Info("Activity Name", HMS.activityName(), 0, null),
    Info("Version Code ID", HMS.versionCodeId(), 0, null),
    Info("JSON Version Min", HMS.jsonVersionMin(), 0, null),
    Info("App ID", HMS.appId(), 0, null)
)

val featureInfo = listOf(
    Info("Bluetooth", Feature.hasBluetooth().literal(), R.drawable.ic_bluetooth, null),
    Info("Bluetooth LE", Feature.hasBluetoothLE().literal(), R.drawable.ic_bluetooth, null),
    Info("NFC", Feature.hasNFC().literal(), R.drawable.ic_nfc, null),
    Info("GPS", Feature.hasGPS().literal(), R.drawable.ic_gps, null),
    Info("WiFi", Feature.hasWifi().literal(), R.drawable.ic_wifi, null),
    Info("Wifi Direct", Feature.hasWifiDirect().literal(), R.drawable.ic_wifi, null),
    Info("Fingerprint", Feature.hasFingerprint().literal(), R.drawable.ic_fingerprint, null),
    Info("Camera AR", Feature.hasCameraAR().literal(), R.drawable.ic_camera, null),

    Info("Proximity Sensor", Feature.hasProximitySensor().literal(), 0, null),
    Info("Accelerometer Sensor", Feature.hasAccelerometerSensor().literal(), 0, null),
    Info("Gyroscope Sensor", Feature.hasGyroscopeSensor().literal(), 0, null),
    Info("Light Sensor", Feature.hasLightSensor().literal(), 0, null),
    Info("Barometer Sensor (air pressure sensor)", Feature.hasBarometerSensor().literal(), 0, null),
    Info("Temperature Sensor", Feature.hasAmbientTemperatureSensor().literal(), 0, null),
    Info("Step Counter Sensor", Feature.hasStepCounterSensor().literal(), 0, null),
    Info("Step Detector Sensor", Feature.hasStepDetectorSensor().literal(), 0, null)
)