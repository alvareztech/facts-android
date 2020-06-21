package tech.alvarez.facts.util

import com.huawei.hms.api.HuaweiApiAvailability
import tech.alvarez.facts.Facts

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
                .isHuaweiMobileServicesAvailable(Facts.applicationContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS
        }

        fun hmsVersion() = Util.versionPackage("com.huawei.hwid")
        fun appGalleryVersion() = Util.versionPackage("com.huawei.appmarket")
    }
}