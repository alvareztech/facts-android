package tech.alvarez.facts

import android.Manifest
import android.graphics.drawable.Drawable
import tech.alvarez.facts.util.*

data class Info(val label: String, val value: String, val icon: Int?, val permission: String?)
data class App(
    val icon: Drawable,
    val name: String,
    val packageName: String,
    val version: String,
    val isSystemPackage: Boolean
)

fun Boolean.literal() = if (this) "Yes" else "No"

class Message {
    companion object {
        const val notAvailable = "Not Available"
        const val requiresPermission = "Requires Permission (tap here)"
        const val none = "None"
    }
}

fun deviceInfo(): List<Info> {
    return listOf(
        Info("Device Name", Feature.getDeviceName(), null, null),
        Info("Manufacturer", Device.manufacturer(), null, null),
        Info("Product", Device.product(), null, null),
        Info("Model", Device.model(), null, null),
        Info("IMEI", Device.imei(), null, Manifest.permission.READ_PHONE_STATE),
        Info("Screen Size", Feature.screenSize(), null, null),
        Info("32 bit ABIs supported", Device.supported32bits(), null, null),
        Info("64 bit ABIs supported", Device.supported64bits(), null, null),
        Info("Java VM", Device.javaVM(), null, null),
        Info("Density", Device.density(), null, null),
        Info("Total Memory", Device.totalMemory(), null, null),
        Info("OpenGL ES Version", Device.openGlVersion(), null, null)
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
    Info("Hardware", Device.hardware(), 0, null),
    Info("EMUI Version", OS.emuiVersion(), R.drawable.ic_huawei, null),
    Info("MIUI Version", OS.miuiVersion(), R.drawable.ic_xiaomi, null)
)

val gmsInfo = listOf(
    Info("Google Play Services Available", GMS.isGMSAvailable().literal(), null, null),
    Info("Google Play Services Version", GMS.playServicesVersion(), null, null),
    Info("Google Play Store Version", GMS.playStoreVersion(), null, null),
    Info("Google Play Services Version Code", GMS.playServicesVersionCode(), null, null),
    Info("Google Play Store Package", GMS.playStorePackage(), null, null),
    Info("Google Play Services Package", GMS.playServicesPackage(), null, null)
)
val hmsInfo = listOf(
    Info("Huawei Mobile Services available", HMS.isHMSAvailable().literal(), null, null),
    Info("Huawei Mobile Services Version", HMS.hmsVersion(), null, null),
    Info("Huawei AppGallery Version", HMS.appGalleryVersion(), null, null),
    Info("SDK Version", HMS.sdkVersion(), null, null),
    Info("Version Code", HMS.versionCode(), null, null),
    Info("Action", HMS.action(), null, null),
    Info("Activity Name", HMS.activityName(), null, null),
    Info("Version Code ID", HMS.versionCodeId(), null, null),
    Info("JSON Version Min", HMS.jsonVersionMin(), null, null),
    Info("App ID", HMS.appId(), null, null)
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

    Info(
        "Proximity Sensor",
        Feature.hasProximitySensor().literal(),
        R.drawable.ic_proximity_sensor,
        null
    ),
    Info("Accelerometer Sensor", Feature.hasAccelerometerSensor().literal(), 0, null),
    Info("Gyroscope Sensor", Feature.hasGyroscopeSensor().literal(), R.drawable.ic_gyroscope, null),
    Info("Light Sensor", Feature.hasLightSensor().literal(), R.drawable.ic_light, null),
    Info(
        "Barometer Sensor (air pressure sensor)",
        Feature.hasBarometerSensor().literal(),
        R.drawable.ic_pressure,
        null
    ),
    Info(
        "Temperature Sensor",
        Feature.hasAmbientTemperatureSensor().literal(),
        R.drawable.ic_temperature,
        null
    ),
    Info("Step Counter Sensor", Feature.hasStepCounterSensor().literal(), 0, null),
    Info("Step Detector Sensor", Feature.hasStepDetectorSensor().literal(), 0, null)
)