package tech.alvarez.facts.util

import com.huawei.hms.api.HuaweiApiAvailability
import tech.alvarez.facts.App

class HMS {
    companion object {

        fun appId(): String = HuaweiApiAvailability.APPID_HMS
        fun action(): String = HuaweiApiAvailability.SERVICES_ACTION
        fun activityName(): String = HuaweiApiAvailability.ACTIVITY_NAME
        fun versionCode(): String = HuaweiApiAvailability.SERVICES_VERSION_CODE.toString()
        fun versionCodeId(): String = HuaweiApiAvailability.HMS_VERSION_CODE_ID.toString()
        fun jsonVersionMin(): String = HuaweiApiAvailability.HMS_JSON_VERSION_MIN.toString()
        fun sdkVersionName(): String = HuaweiApiAvailability.HMS_SDK_VERSION_NAME
        fun sdkVersionCode(): String = HuaweiApiAvailability.HMS_SDK_VERSION_CODE.toString()

        fun isHMSAvailable(): Boolean {
            return HuaweiApiAvailability.getInstance()
                .isHuaweiMobileServicesAvailable(App.applicationContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS
        }
    }
}