package org.wit.freedomfood_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import org.wit.freedomfood_android.R
import org.wit.freedomfood_android.databinding.ActivityFreedomfoodBinding
import org.wit.freedomfood_android.main.MainApp
import org.wit.freedomfood_android.models.FreedomFoodModel
import timber.log.Timber.i

class FreedomFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFreedomfoodBinding
    var freedomfood = FreedomFoodModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreedomfoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp
        i("FreedomFood Activity started...")

        if (intent.hasExtra("freedomfood_edit")) {
            freedomfood = intent.extras?.getParcelable("freedomfood_edit")!!
            binding.freedomfoodTitle.setText(freedomfood.title)
            binding.description.setText(freedomfood.description)
            binding.btnAdd.setText(R.string.save_freedomfood)
        }

        binding.btnAdd.setOnClickListener() {
            freedomfood.title = binding.freedomfoodTitle.text.toString()
            freedomfood.description = binding.description.text.toString()
            if (freedomfood.title.isNotEmpty()) {
                app.freedomfoods.create(freedomfood.copy())
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,R.string.enter_freedomfood_title, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_freedomfood, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}