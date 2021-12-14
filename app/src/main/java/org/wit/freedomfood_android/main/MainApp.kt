package org.wit.freedomfood_android.main

import android.app.Application
import org.wit.freedomfood_android.models.FreedomFoodMemStore
import org.wit.freedomfood_android.models.FreedomFoodStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var freedomfoods: FreedomFoodStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        freedomfoods = FreedomFoodMemStore()
        i("FreedomFood started")
    }
}