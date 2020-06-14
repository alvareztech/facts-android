package tech.alvarez.facts.util

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.pm.ConfigurationInfo
import android.content.pm.FeatureInfo
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import tech.alvarez.facts.App
import tech.alvarez.facts.Message


class Device {
    companion object {
        fun manufacturer(): String = Build.MANUFACTURER
        fun product(): String = Build.PRODUCT
        fun model(): String = Build.MODEL
        fun board(): String = Build.BOARD
        fun display(): String = Build.DISPLAY
        fun hardware(): String = Build.HARDWARE
        fun imei(): String {
            val telephonyManager =
                App.applicationContext()
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (ActivityCompat.checkSelfPermission(
                    App.applicationContext(),
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return Message.requiresPermission
            }
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                telephonyManager.imei
            } else {
                telephonyManager.deviceId
            }
        }

        fun supported32bits(): String {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return if (Build.SUPPORTED_32_BIT_ABIS.isNotEmpty()) {
                    Build.SUPPORTED_32_BIT_ABIS.joinToString()
                } else {
                    Message.none
                }
            }
            return Message.notAvailable
        }

        fun supported64bits(): String {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return if (Build.SUPPORTED_64_BIT_ABIS.isNotEmpty()) {
                    Build.SUPPORTED_64_BIT_ABIS.joinToString()
                } else {
                    Message.none
                }
            }
            return Message.notAvailable
        }

        fun javaVM(): String {
            val vmVersion = System.getProperty("java.vm.version")
            vmVersion?.let {
                return if (it.startsWith("2")) "ART $it" else "Dalvik $it"
            }
            return Message.notAvailable
        }

        fun totalMemory(): String {
            val actManager: ActivityManager = App.applicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val memInfo: ActivityManager.MemoryInfo = ActivityManager.MemoryInfo()
            actManager.getMemoryInfo(memInfo)
            return memInfo.totalMem.toString()
        }

        fun density(): String {
            val metrics = DisplayMetrics()
            val windowManager =
                App.applicationContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(metrics)
            return when (metrics.densityDpi) {
                DisplayMetrics.DENSITY_LOW -> "Low " + metrics.densityDpi.toString() + " dpi"
                DisplayMetrics.DENSITY_MEDIUM -> "Medium " + metrics.densityDpi.toString() + " dpi"
                DisplayMetrics.DENSITY_HIGH -> "High " + metrics.densityDpi.toString() + " dpi"
                DisplayMetrics.DENSITY_XHIGH -> "XHigh " + metrics.densityDpi.toString() + " dpi"
                DisplayMetrics.DENSITY_XXHIGH -> "XXHigh " + metrics.densityDpi.toString() + " dpi"
                DisplayMetrics.DENSITY_XXXHIGH -> "XXXHigh " + metrics.densityDpi.toString() + " dpi"
                else -> metrics.densityDpi.toString() + " dpi"
            }
        }

        fun openGlVersion() = openGlVersionFromActivityManager()

        private fun openGlVersionFromActivityManager(): String {
            val activityManager = App.applicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val configInfo = activityManager.deviceConfigurationInfo
            return if (configInfo.reqGlEsVersion != ConfigurationInfo.GL_ES_VERSION_UNDEFINED) {
                configInfo.glEsVersion
            } else {
                "1" // 1 shl 16 // Lack of property means OpenGL ES version 1
            }
        }

        private fun openGlVersionFromPackageManager(): Int {
            val packageManager = App.applicationContext().packageManager
            val featureInfos = packageManager.systemAvailableFeatures
            if (featureInfos.isNotEmpty()) {
                for (featureInfo in featureInfos) {
                    // Null feature name means this feature is the open gl es version feature.
                    if (featureInfo.name == null) {
                        return if (featureInfo.reqGlEsVersion != FeatureInfo.GL_ES_VERSION_UNDEFINED) {
                            featureInfo.reqGlEsVersion
                        } else {
                            1 shl 16 // Lack of property means OpenGL ES version 1
                        }
                    }
                }
            }
            return 1
        }
    }
}