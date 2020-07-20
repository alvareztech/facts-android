package tech.alvarez.facts.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import tech.alvarez.facts.BuildConfig
import tech.alvarez.facts.Facts

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.log(message: String) {
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message)
}

fun Fragment.shareText(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Fragment.openApp(packageName: String) {
    val pm = Facts.applicationContext().packageManager
    val intent = pm.getLaunchIntentForPackage(packageName)
    intent?.let {
        it.addCategory(Intent.CATEGORY_LAUNCHER)
        try {
            startActivity(it)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}

fun Fragment.openInTheMarket(packageName: String) = try {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    Facts.applicationContext().startActivity(intent)
} catch (e: ActivityNotFoundException) {
    Facts.applicationContext().toast("There is no market app available. Opening on the web.")
    openPlayStoreWeb(packageName)
}

private fun Fragment.openWeb(urlString: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    Facts.applicationContext().startActivity(intent)
}

fun Fragment.openPlayStoreWeb(packageName: String) {
    openWeb("https://play.google.com/store/apps/details?id=$packageName")
}

fun Fragment.openAppGalleryWeb(packageName: String) {
    openWeb("https://appgallery1.huawei.com/#/search/$packageName")
}