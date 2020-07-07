package tech.alvarez.facts.util

import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.ads.identifier.AdvertisingIdClient
import com.huawei.hms.api.HuaweiApiAvailability
import com.huawei.hms.api.HuaweiServicesNotAvailableException
import tech.alvarez.facts.Facts
import tech.alvarez.facts.Message
import java.io.IOException
import java.util.concurrent.TimeoutException

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

        fun aaid(): String {
            val instance = HmsInstanceId.getInstance(Facts.applicationContext())
            return instance.id
        }
        // Open Advertising Identifier
        fun oaid() = try {
            val info = AdvertisingIdClient.getAdvertisingIdInfo(Facts.applicationContext())
            info.id
        } catch (e: IOException) {
            "${Message.notAvailable} (IO)"
        } catch (e: HuaweiServicesNotAvailableException) {
            "(Huawei Services Not Available)"
        } catch (e: TimeoutException) {
            "${Message.notAvailable} (Timeout)"
        } catch (e: InterruptedException) {
            "${Message.notAvailable} (Interrupted)"
        } catch (e: Exception) {
            e.printStackTrace()
            Message.notAvailable
        }
    }
}