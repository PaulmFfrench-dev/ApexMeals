package ie.wit.apexmeals.main

import android.app.Application
import timber.log.Timber

class ApexMealsApp : Application() {

    //lateinit var apexmealsStore: ApexMealStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        //  apexmealsStore = ApexMealManager()
        Timber.i("Apex Meal Application Started")
    }
}