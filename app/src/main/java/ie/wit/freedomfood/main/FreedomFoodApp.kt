package ie.wit.freedomfood.main

import android.app.Application
import timber.log.Timber

class FreedomFoodApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Starting FreedomFood Application")
    }
}