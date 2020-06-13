package tech.alvarez.facts.util

import android.os.Build
import com.huawei.hms.api.HuaweiApiAvailability
import tech.alvarez.facts.App
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


class HMS {
    companion object {
        fun appId(): String = HuaweiApiAvailability.APPID_HMS
        fun action(): String = HuaweiApiAvailability.SERVICES_ACTION
        fun activityName(): String = HuaweiApiAvailability.ACTIVITY_NAME
        fun versionCode() = HuaweiApiAvailability.SERVICES_VERSION_CODE.toString()
        fun versionCodeId() = HuaweiApiAvailability.HMS_VERSION_CODE_ID.toString()
        fun jsonVersionMin() = HuaweiApiAvailability.HMS_JSON_VERSION_MIN.toString()
        fun sdkVersion() =
            "${HuaweiApiAvailability.HMS_SDK_VERSION_NAME} (${HuaweiApiAvailability.HMS_SDK_VERSION_CODE})"

        fun isHMSAvailable(): Boolean {
            return HuaweiApiAvailability.getInstance()
                .isHuaweiMobileServicesAvailable(App.applicationContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS
        }

        fun hmsVersion() = Util.versionPackage("com.huawei.hwid")
        fun appGalleryVersion() = Util.versionPackage("com.huawei.appmarket")

        // https://stackoverflow.com/questions/40552878/get-android-miui-version-information-programmatically

        fun miuiVersion(): String {
            try {
                val propertyClass = Class.forName("android.os.SystemProperties")
                val method: Method = propertyClass.getMethod("get", String::class.java)
                var versionCode = method.invoke(propertyClass, "ro.miui.ui.version.code") as String
                var versionName = method.invoke(propertyClass, "ro.miui.ui.version.name") as String
                return "$versionName ($versionCode)"
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
            return ""
        }

        // https://stackoverflow.com/questions/61352817/get-emui-version-of-a-device-programmatically

        fun readEMUIVersion(): String {
            try {
                val propertyClass = Class.forName("android.os.SystemProperties")
                val method: Method = propertyClass.getMethod("get", String::class.java)
                var versionEmui = method.invoke(propertyClass, "ro.build.version.emui") as String
                if (versionEmui.startsWith("EmotionUI_")) {
                    versionEmui = versionEmui.substring(10, versionEmui.length)
                }
                return versionEmui
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
            return ""
        }

        fun emuiVersion(): String {
            return try {
                val line: String = Build.DISPLAY
                val spaceIndex = line.indexOf(" ")
                val lastIndex = line.indexOf("(")
                if (lastIndex != -1) {
                    line.substring(spaceIndex, lastIndex)
                } else line.substring(spaceIndex)
            } catch (e: Exception) {
                ""
            }
        }
    }
}