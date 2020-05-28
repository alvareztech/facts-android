package tech.alvarez.facts.util

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import tech.alvarez.facts.App


class Feature {
    companion object {

        fun hasBluetooth(): Boolean {
            val packageManager: PackageManager = App.applicationContext().packageManager
            return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)
        }

        fun hasBluetoothLE(): Boolean {
            val packageManager: PackageManager = App.applicationContext().packageManager
            return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
        }

        fun getDeviceName(): String {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer)) {
                model
            } else "$manufacturer $model"
        }

        fun screenSize(): String {
            val screenSize: Int = App.applicationContext().resources.configuration.screenLayout and
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