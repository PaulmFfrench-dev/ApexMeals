package org.wit.freedomfood_android.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.freedomfood_android.R
import org.wit.freedomfood_android.databinding.ActivityFreedomfoodBinding
import org.wit.freedomfood_android.helpers.showImagePicker
import org.wit.freedomfood_android.main.MainApp
import org.wit.freedomfood_android.models.FreedomFoodModel
import org.wit.freedomfood_android.models.Location
import timber.log.Timber.i

class FreedomFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFreedomfoodBinding
    var freedomfood = FreedomFoodModel()
    lateinit var app : MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    //var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false
        binding = ActivityFreedomfoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp
        i("FreedomFood Activity started...")

        if (intent.hasExtra("freedomfood_edit")) {
            edit = true
            freedomfood = intent.extras?.getParcelable("freedomfood_edit")!!
            binding.freedomfoodTitle.setText(freedomfood.title)
            binding.description.setText(freedomfood.description)
            binding.btnAdd.setText(R.string.save_freedomfood)
            Picasso.get()
                .load(freedomfood.image)
                .into(binding.freedomfoodImage)
            if (freedomfood.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_freedomfood_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            freedomfood.title = binding.freedomfoodTitle.text.toString()
            freedomfood.description = binding.description.text.toString()
            if (freedomfood.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_freedomfood_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.freedomfoods.update(freedomfood.copy())
                } else {
                    app.freedomfoods.create(freedomfood.copy())
                }
            }
            i("add Button Pressed: $freedomfood")
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        binding.freedomfoodLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (freedomfood.zoom != 0f) {
                location.lat =  freedomfood.lat
                location.lng = freedomfood.lng
                location.zoom = freedomfood.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerImagePickerCallback()
        registerMapCallback()
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            freedomfood.image = result.data!!.data!!
                            Picasso.get()
                                .load(freedomfood.image)
                                .into(binding.freedomfoodImage)
                            binding.chooseImage.setText(R.string.change_freedomfood_image)
                        }
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            freedomfood.lat = location.lat
                            freedomfood.lng = location.lng
                            freedomfood.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}