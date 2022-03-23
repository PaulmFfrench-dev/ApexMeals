package ie.wit.apexmeals.main

import android.app.Application
import ie.wit.apexmeals.models.ApexMealsManager
import ie.wit.apexmeals.models.ApexMealsStore
import timber.log.Timber

class ApexMealsApp : Application() {

    //lateinit var apexmealsStore: ApexMealsStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
//        apexmealsStore = ApexMealsManager()
        Timber.i("Apex Meals Application Started")
    }
}