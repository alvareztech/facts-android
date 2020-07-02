package tech.alvarez.facts.util

import android.annotation.SuppressLint
import android.os.Build
import tech.alvarez.facts.Message
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class OS {
    companion object {
        fun sdk(): String = Build.VERSION.SDK_INT.toString()
        fun baseOS(): String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Build.VERSION.BASE_OS
        } else {
            Message.notAvailable
        }

        fun versionCodename(): String = Build.VERSION.CODENAME
        fun versionRelease(): String = Build.VERSION.RELEASE

        fun securityPath(): String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Build.VERSION.SECURITY_PATCH
        } else {
            Message.notAvailable
        }

        @SuppressLint("PrivateApi")
        fun miuiVersion(): String {
            try {
                val propertyClass = Class.forName("android.os.SystemProperties")
                val method: Method = propertyClass.getMethod("get", String::class.java)
                val versionCode = method.invoke(propertyClass, "ro.miui.ui.version.code") as String
                val versionName = method.invoke(propertyClass, "ro.miui.ui.version.name") as String
                return if (versionName.isNotEmpty()) {
                    "$versionName ($versionCode)"
                } else {
                    Message.notAvailable
                }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
            return Message.notAvailable
        }

        @SuppressLint("PrivateApi")
        fun emuiVersion(): String {
            try {
                val propertyClass = Class.forName("android.os.SystemProperties")
                val method: Method = propertyClass.getMethod("get", String::class.java)
                var versionEmui = method.invoke(propertyClass, "ro.build.version.emui") as String
                if (versionEmui.startsWith("EmotionUI_")) {
                    versionEmui = versionEmui.substring(10, versionEmui.length)
                }
                return versionEmui.ifEmpty { Message.notAvailable }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
            return Message.notAvailable
        }

        private fun emuiVersionAlternative(): String {
            return try {
                val line: String = Build.DISPLAY
                val spaceIndex = line.indexOf(" ")
                val lastIndex = line.indexOf("(")
                if (lastIndex != -1) {
                    line.substring(spaceIndex, lastIndex)
                } else line.substring(spaceIndex)
            } catch (e: Exception) {
                Message.notAvailable
            }
        }
    }
}