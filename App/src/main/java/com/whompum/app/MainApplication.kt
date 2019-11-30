package com.whompum.app

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Base [Application] class for this project.
 * This includes configuration for [Timber] and [Dagger].
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }

}