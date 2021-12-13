package org.wit.freedomfood_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.freedomfood_android.R
import org.wit.freedomfood_android.main.MainApp

class FreedomFoodListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freedom_food_list)
        app = application as MainApp
    }
}