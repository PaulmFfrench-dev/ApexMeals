package org.wit.freedomfood_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber
import timber.log.Timber.i

class FreedomFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freedomfood)

        Timber.plant(Timber.DebugTree())

        i("FreedomFood Activity started...")
    }
}