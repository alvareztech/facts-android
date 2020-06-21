package tech.alvarez.facts.util

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import tech.alvarez.facts.Facts

class Feature {
    companion object {
        fun hasBluetooth() = isFeatureAvailable(PackageManager.FEATURE_BLUETOOTH)
        fun hasBluetoothLE() = isFeatureAvailable(PackageManager.FEATURE_BLUETOOTH_LE)
        fun hasNFC() = isFeatureAvailable(PackageManager.FEATURE_NFC)
        fun hasGPS() = isFeatureAvailable(PackageManager.FEATURE_LOCATION_GPS)

        fun hasProximitySensor() = isFeatureAvailable(PackageManager.FEATURE_SENSOR_PROXIMITY)
        fun hasAccelerometerSensor() =
            isFeatureAvailable(PackageManager.FEATURE_SENSOR_ACCELEROMETER)

        fun hasGyroscopeSensor() = isFeatureAvailable(PackageManager.FEATURE_SENSOR_GYROSCOPE)
        fun hasLightSensor() = isFeatureAvailable(PackageManager.FEATURE_SENSOR_LIGHT)
        fun hasBarometerSensor() = isFeatureAvailable(PackageManager.FEATURE_SENSOR_BAROMETER)
        fun hasAmbientTemperatureSensor() =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                isFeatureAvailable(PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE)
            } else {
                false
            }

        fun hasStepCounterSensor() = isFeatureAvailable(PackageManager.FEATURE_SENSOR_STEP_COUNTER)
        fun hasStepDetectorSensor() =
            isFeatureAvailable(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)

        fun hasFingerprint() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isFeatureAvailable(PackageManager.FEATURE_FINGERPRINT)
        } else {
            false
        }

        fun hasWifi() = isFeatureAvailable(PackageManager.FEATURE_WIFI)
        fun hasWifiDirect() = isFeatureAvailable(PackageManager.FEATURE_WIFI_DIRECT)
        fun hasCameraAR() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            isFeatureAvailable(PackageManager.FEATURE_CAMERA_AR)
        } else {
            false
        }

        private fun isFeatureAvailable(feature: String): Boolean {
            val packageManager: PackageManager = Facts.applicationContext().packageManager
            return packageManager.hasSystemFeature(feature)
        }

        fun getDeviceName(): String {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer)) {
                model
            } else "$manufacturer $model"
        }

        fun screenSize(): String {
            val screenSize: Int =
                Facts.applicationContext().resources.configuration.screenLayout and
                        Configuration.SCREENLAYOUT_SIZE_MASK

            val message: String
            message = when (screenSize) {
                Configuration.SCREENLAYOUT_SIZE_LARGE -> "Large screen"
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> "Normal screen"
                Configuration.SCREENLAYOUT_SIZE_SMALL -> "Small screen"
                else -> "Screen size is neither large, normal or small"
            }
            return message
        }
    }
}