package tech.alvarez.facts

import android.app.Application
import android.content.Context

class Facts : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: Facts? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}