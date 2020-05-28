package tech.alvarez.facts.util

import com.huawei.hms.api.HuaweiApiAvailability
import tech.alvarez.facts.App

class HMS {
    companion object {

        fun appId(): String = HuaweiApiAvailability.APPID_HMS
        fun sdkVersionName(): String = HuaweiApiAvailability.HMS_SDK_VERSION_NAME
        fun sdkVersionCode(): String = HuaweiApiAvailability.HMS_SDK_VERSION_CODE.toString()

        fun isHMSAvailable(): Boolean {
            return HuaweiApiAvailability.getInstance()
                .isHuaweiMobileServicesAvailable(App.applicationContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS
        }
    }
}