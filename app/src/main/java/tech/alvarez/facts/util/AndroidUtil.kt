package tech.alvarez.facts.util

import android.content.Intent
import androidx.fragment.app.Fragment
import tech.alvarez.facts.Facts

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