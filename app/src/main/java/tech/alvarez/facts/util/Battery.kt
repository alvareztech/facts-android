package tech.alvarez.facts.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import tech.alvarez.facts.Facts
import tech.alvarez.facts.Info
import tech.alvarez.facts.Message

class Battery {
    companion object {
        fun infoList(): List<Info> {
            val intent = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let {
                Facts.applicationContext().registerReceiver(null, it)
            }
            intent?.let { i ->
                return mutableListOf<Info>().apply {
                    add(Info("Level", "${percentage(i)} %", null, null))
                    add(Info("Status", status(i), null, null))
                    add(Info("Temperature", temperature(i), null, null))
                    add(Info("Voltage", voltage(i), null, null))
                    add(Info("Capacity", capacity(), null, null))
                    add(Info("Health", health(i), null, null))
                    add(Info("Plugged", plugged(i), null, null))
                    add(Info("Technology", technology(i), null, null))
                }
            }
            return emptyList()
        }

        private fun percentage(intent: Intent): Int {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            return (level * 100 / scale.toFloat()).toInt()
        }

        private fun plugged(intent: Intent) =
            when (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)) {
                BatteryManager.BATTERY_PLUGGED_AC -> "Yes (AC)"
                BatteryManager.BATTERY_PLUGGED_USB -> "Yes (USB)"
                BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Yes (Wireless)"
                else -> "No"
            }

        private fun status(intent: Intent) =
            when (intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0)) {
                BatteryManager.BATTERY_STATUS_UNKNOWN -> "Unknown"
                BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not Charging"
                BatteryManager.BATTERY_STATUS_FULL -> "Full"
                else -> Message.notAvailable
            }
        // TODO: Avoid non-null assert
        private fun technology(intent: Intent) =
            intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY).ifBlank { Message.notAvailable }

        private fun voltage(intent: Intent) =
            if (intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) > 0) {
                "${intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)} mV"
            } else {
                Message.notAvailable
            }

        private fun temperature(intent: Intent): String {
            val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
            return if (temperature > 0) {
                "${temperature / 10} °C"
            } else {
                Message.notAvailable
            }
        }

        /**
         * Reference: https://developer.android.com/reference/android/os/BatteryManager
         */
        private fun health(intent: Intent) =
            when (intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0)) {
                BatteryManager.BATTERY_HEALTH_UNKNOWN -> "Unknown"
                BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
                BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
                BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
                BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over voltage"
                BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified Failure"
                BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
                else -> Message.notAvailable
            }

        @SuppressLint("PrivateApi")
        fun capacity() = try {
            val powerProfile = Class.forName("com.android.internal.os.PowerProfile")
                .getConstructor(Context::class.java)
                .newInstance(Facts.applicationContext())
            val batteryCapacity = Class.forName("com.android.internal.os.PowerProfile")
                .getMethod("getBatteryCapacity")
                .invoke(powerProfile) as Double
            "${batteryCapacity.toInt()} mAh"
        } catch (e: Exception) {
            e.printStackTrace()
            val impreciseCapacity = impreciseCapacity()
            if (impreciseCapacity() > 0) {
                "$impreciseCapacity mAh (may not be accurate)"
            } else {
                Message.notAvailable
            }
        }

        private fun impreciseCapacity(): Long {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val batteryManager = Facts.applicationContext()
                    .getSystemService(Context.BATTERY_SERVICE) as BatteryManager
                val chargeCounter =
                    batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER)
                val capacity =
                    batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
                return (chargeCounter.toFloat() / capacity.toFloat() * 100f).toLong()
            }
            return 0
        }
    }
}