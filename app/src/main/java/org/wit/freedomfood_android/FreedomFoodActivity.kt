package org.wit.freedomfood_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.freedomfood_android.databinding.ActivityFreedomfoodBinding
import timber.log.Timber
import timber.log.Timber.i

class FreedomFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFreedomfoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFreedomfoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("FreedomFood Activity started...")

        binding.btnAdd.setOnClickListener() {
            val placemarkTitle = binding.freedomfoodTitle.text.toString()
            if (placemarkTitle.isNotEmpty()) {
                i("add Button Pressed: $placemarkTitle")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
}