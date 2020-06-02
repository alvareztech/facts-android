package tech.alvarez.facts.util

import android.os.Build
import tech.alvarez.facts.Message

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
    }
}