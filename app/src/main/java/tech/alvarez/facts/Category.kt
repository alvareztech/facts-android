package tech.alvarez.facts

enum class Category(val icon: Int, val title: Int) {
    DEVICE(R.drawable.ic_phone, R.string.device),
    OS(R.drawable.ic_adb, R.string.os),
    HMS(R.drawable.ic_huawei, R.string.hms),
    GMS(R.drawable.ic_google_play, R.string.gms),
    FEATURES(R.drawable.ic_memory, R.string.features),
    BATTERY(R.drawable.ic_battery_full, R.string.battery),
    APPS(R.drawable.ic_apps, R.string.apps),
    PACKAGES(R.drawable.ic_box_settings, R.string.packages),
}