package tech.alvarez.facts.util

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import tech.alvarez.facts.App
import tech.alvarez.facts.Message

private const val TAG = "FACTS"

class Util {
    companion object {

        fun appVersion() = versionPackage(App.applicationContext().packageName)

        fun versionPackage(packageName: String): String {
            return try {
                val packageInfo: PackageInfo =
                    App.applicationContext().packageManager.getPackageInfo(packageName, 0)
                val versionName = packageInfo.versionName
                val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    packageInfo.versionCode
                }
                "$versionName ($versionCode)"
            } catch (e: PackageManager.NameNotFoundException) {
                Message.notAvailable
            }
        }

        private fun listPackageManager() {
            val installedPackages = App.applicationContext().packageManager.getInstalledPackages(0)
            Log.d(TAG, "installedPackages: ${installedPackages.size}")
            for (installedPackage in installedPackages) {
                Log.d(
                    TAG, "installedPackages: ${installedPackage.applicationInfo.packageName}"
                )
            }

            val installedApps = App.applicationContext().packageManager.getInstalledApplications(0)
            Log.d(TAG, "installedApps: ${installedApps.size}")
            for (installedApp in installedApps) {
                Log.d(TAG, "installedApps: ${installedApp.packageName}")
            }

            val mainIntent = Intent(Intent.ACTION_MAIN, null)
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            val intents =
                App.applicationContext().packageManager.queryIntentActivities(mainIntent, 0)
            Log.d(TAG, "IntentActivities: ${intents.size}")
            for (resolveInfo in intents) {
                Log.d(TAG, "IntentActivities: ${resolveInfo.activityInfo.packageName}")
            }
        }
    }
}