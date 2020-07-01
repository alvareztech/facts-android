package tech.alvarez.facts.util

import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import tech.alvarez.facts.Facts
import tech.alvarez.facts.Message
import java.io.IOException
import java.util.concurrent.TimeoutException

class GMS {
    companion object {
        fun playStorePackage(): String = GoogleApiAvailability.GOOGLE_PLAY_STORE_PACKAGE
        fun playServicesPackage(): String = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE
        fun playServicesVersionCode(): String =
            GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE.toString()

        fun isGMSAvailable(): Boolean {
            return GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(Facts.applicationContext()) == ConnectionResult.SUCCESS
        }

        fun playServicesVersion() = Util.versionPackage("com.google.android.gms")
        fun playStoreVersion() = Util.versionPackage("com.android.vending")

        // Google Advertising ID
        fun gaid() = try {
            val info = AdvertisingIdClient.getAdvertisingIdInfo(Facts.applicationContext())
            info.id
        } catch (e: IOException) {
            "${Message.notAvailable} (IO)"
        } catch (e: GooglePlayServicesNotAvailableException) {
            "(Google Play Services Not Available)"
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