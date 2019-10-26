package com.whompum.app

import android.app.Application
import timber.log.Timber

/**
 * Base [Application] class for this project.
 * This includes configuration for [Timber] and [Dagger].
 */
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //if (BuildConfig.enableLogs)
            //Timber.plant(Timber.asTree())
    }

}