package org.wit.freedomfood_android.main

import android.app.Application
import org.wit.freedomfood_android.models.FreedomFoodModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val freedomfoods = ArrayList<FreedomFoodModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("FreedomFood started")
    }
}