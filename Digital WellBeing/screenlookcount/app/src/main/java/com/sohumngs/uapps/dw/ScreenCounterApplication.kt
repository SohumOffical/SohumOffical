package com.sohumngs.uapps.dw

import android.app.Application
import com.sohumngs.uapps.dw.utils.AppPreferences

/**
 * Application extension class for initializing Shared Preferences instance.
 *
 * @author Antonina
 */
class ScreenCounterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}