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
    val freedomfoods = ArrayList<FreedomFoodModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreedomfoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        i("FreedomFood Activity started...")

        binding.btnAdd.setOnClickListener() {
            freedomfood.title = binding.freedomfoodTitle.text.toString()
            if (freedomfood.title.isNotEmpty()) {
                freedomfoods.add(freedomfood.copy())
                i("add Button Pressed: ${freedomfood}")
                for (i in freedomfoods.indices)
                { i("FreedomFoods[$i]:${this.freedomfoods[i]}") }
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}