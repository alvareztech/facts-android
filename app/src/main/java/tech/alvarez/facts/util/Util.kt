package tech.alvarez.facts.util

import android.app.ActivityManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import tech.alvarez.facts.App

class Util {
    companion object {
        val securityPath: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            android.os.Build.VERSION.SECURITY_PATCH
        } else {
            "Not available"
        }

        fun androidVersion(): String {
            return android.os.Build.VERSION.CODENAME
        }

        fun androidRelease(): String {
            return android.os.Build.VERSION.RELEASE
        }

        fun getDeviceName(): String {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer)) {
                model
            } else "$manufacturer $model"
        }

        val manufacturer: String = Build.MANUFACTURER
        val product: String = Build.PRODUCT
        val model: String = Build.MODEL
        val board: String = Build.BOARD
        val display: String = Build.DISPLAY
        val hardware: String = Build.HARDWARE

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

        fun supported32bits(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Build.SUPPORTED_32_BIT_ABIS.contentToString()
            } else {
                return "Not available"
            }
        }

        fun supported64bits(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Build.SUPPORTED_64_BIT_ABIS.contentToString()
            } else {
                return "Not available"
            }
        }

        fun totalMemory(): String {
            val actManager: ActivityManager = App.applicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val memInfo: ActivityManager.MemoryInfo = ActivityManager.MemoryInfo()
            actManager.getMemoryInfo(memInfo)
            return memInfo.totalMem.toString()
        }
    }
}