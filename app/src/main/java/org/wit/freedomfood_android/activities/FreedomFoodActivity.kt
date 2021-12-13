package org.wit.freedomfood_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.freedomfood_android.databinding.ActivityFreedomfoodBinding
import org.wit.freedomfood_android.models.FreedomFoodModel
import timber.log.Timber
import timber.log.Timber.i

class FreedomFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFreedomfoodBinding
    var freedomfood = FreedomFoodModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFreedomfoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("FreedomFood Activity started...")

        binding.btnAdd.setOnClickListener() {
            freedomfood.title = binding.freedomfoodTitle.text.toString()
            if (freedomfood.title.isNotEmpty()) {
                i("add Button Pressed: $freedomfood.title")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}