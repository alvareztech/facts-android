package tech.alvarez.facts.util

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import tech.alvarez.facts.App
import tech.alvarez.facts.Facts
import tech.alvarez.facts.Message

private const val TAG = "FACTS"

class Util {
    companion object {

        fun appVersion() = versionPackage(Facts.applicationContext().packageName)

        fun versionPackage(packageName: String): String {
            return try {
                val packageInfo: PackageInfo =
                    Facts.applicationContext().packageManager.getPackageInfo(packageName, 0)
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

        fun appList2(): List<App> {
            val list = mutableListOf<App>()
            val pm = Facts.applicationContext().packageManager
            val installedApps = pm.getInstalledPackages(0)
            Log.d(TAG, "getInstalledPackages: ${installedApps.size}")
            for (app in installedApps) {
                list.add(
                    App(
                        app.applicationInfo.loadIcon(pm),
                        app.applicationInfo.loadLabel(pm).toString(),
                        app.packageName,
                        "${app.versionName} (${app.versionCode})"
//                        if (app.loadDescription(pm) == null) "" else app.loadDescription(pm)
                            .toString()
                    )
                )
            }
            return list
        }

        fun appList(): List<App> {
            val list = mutableListOf<App>()
            val pm = Facts.applicationContext().packageManager
            val installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
            Log.d(TAG, "getInstalledApplications: ${installedApps.size}")
            for (app in installedApps) {
                list.add(
                    App(
                        app.loadIcon(pm),
                        app.loadLabel(pm).toString(),
                        app.packageName,
                        app.minSdkVersion.toString()
//                        if (app.loadDescription(pm) == null) "" else app.loadDescription(pm)
                            .toString()
                    )
                )
            }
            return list
        }

        fun userApps(): List<App> {
            val apps = mutableListOf<App>()
            val pm = Facts.applicationContext().packageManager
            val intent = Intent(Intent.ACTION_MAIN, null)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val userApps = pm.queryIntentActivities(intent, 0)
            for (app in userApps) {
                val packageName = app.activityInfo.applicationInfo.packageName
                apps.add(
                    App(
                        app.loadIcon(pm),
                        app.loadLabel(pm).toString(),
                        packageName,
                        Util.versionPackage(packageName)
                    )
                )
            }
            return apps
        }

        private fun listPackageManager() {
            val installedPackages =
                Facts.applicationContext().packageManager.getInstalledPackages(0)
            Log.d(TAG, "installedPackages: ${installedPackages.size}")
            for (installedPackage in installedPackages) {
                Log.d(
                    TAG, "installedPackages: ${installedPackage.applicationInfo.packageName}"
                )
            }

            val installedApps =
                Facts.applicationContext().packageManager.getInstalledApplications(0)
            Log.d(TAG, "installedApps: ${installedApps.size}")
            for (installedApp in installedApps) {
                Log.d(TAG, "installedApps: ${installedApp.packageName}")
            }

            val mainIntent = Intent(Intent.ACTION_MAIN, null)
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            val intents =
                Facts.applicationContext().packageManager.queryIntentActivities(mainIntent, 0)
            Log.d(TAG, "IntentActivities: ${intents.size}")
            for (resolveInfo in intents) {
                Log.d(TAG, "IntentActivities: ${resolveInfo.activityInfo.packageName}")
            }
        }
    }
}