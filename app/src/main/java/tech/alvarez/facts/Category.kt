package tech.alvarez.facts

import androidx.fragment.app.Fragment
import tech.alvarez.facts.apps.AppsFragment
import tech.alvarez.facts.info.InfoFragment

enum class Category(val icon: Int, val title: Int) {
    DEVICE(R.drawable.ic_phone, R.string.device) {
        override fun fragment() = InfoFragment()
    },
    OS(R.drawable.ic_adb, R.string.os) {
        override fun fragment() = InfoFragment()
    },
    HMS(R.drawable.ic_huawei, R.string.hms) {
        override fun fragment() = InfoFragment()
    },
    GMS(R.drawable.ic_google_play, R.string.gms) {
        override fun fragment() = InfoFragment()
    },
    FEATURES(R.drawable.ic_memory, R.string.features) {
        override fun fragment() = InfoFragment()
    },
    BATTERY(R.drawable.ic_battery_full, R.string.battery) {
        override fun fragment() = InfoFragment()
    },
    USER_APPS(R.drawable.ic_apps, R.string.user_apps) {
        override fun fragment() = AppsFragment()
    },
    SYSTEM_APPS(R.drawable.ic_apps, R.string.system_apps) {
        override fun fragment() = AppsFragment()
    },
    PACKAGES(R.drawable.ic_box_settings, R.string.packages) {
        override fun fragment() = AppsFragment()
    };

    abstract fun fragment(): Fragment
}