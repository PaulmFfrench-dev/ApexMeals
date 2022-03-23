package ie.wit.freedomfood.main

import android.app.Application
import ie.wit.freedomfood.models.FreedomFoodMemStore
import ie.wit.freedomfood.models.FreedomFoodStore
import timber.log.Timber

class FreedomFoodApp : Application() {

    lateinit var freedomfoodsStore: FreedomFoodStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        freedomfoodsStore = FreedomFoodMemStore()
        Timber.i("FreedomFood Application Started")
    }
}