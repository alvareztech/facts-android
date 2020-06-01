package tech.alvarez.facts.util

import tech.alvarez.facts.Message

class OS {
    companion object {

        fun sdk(): String = android.os.Build.VERSION.SDK_INT.toString()
        fun baseOS(): String = android.os.Build.VERSION.BASE_OS
        fun versionCodename(): String = android.os.Build.VERSION.CODENAME
        fun versionRelease(): String = android.os.Build.VERSION.RELEASE

        fun securityPath(): String {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                return android.os.Build.VERSION.SECURITY_PATCH
            }
            return Message.notAvailable
        }
    }
}