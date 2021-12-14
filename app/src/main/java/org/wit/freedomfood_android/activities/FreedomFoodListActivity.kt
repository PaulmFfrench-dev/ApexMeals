package org.wit.freedomfood_android.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.freedomfood_android.R
import org.wit.freedomfood_android.adapters.FreedomFoodAdapter
import org.wit.freedomfood_android.adapters.FreedomFoodListener
import org.wit.freedomfood_android.databinding.ActivityFreedomfoodListBinding
import org.wit.freedomfood_android.main.MainApp
import org.wit.freedomfood_android.models.FreedomFoodModel

class FreedomFoodListActivity : AppCompatActivity(), FreedomFoodListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityFreedomfoodListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreedomfoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadFreedomFoods()
        registerRefreshCallback()
        registerMapCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, FreedomFoodActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
            R.id.item_map -> {
                val launcherIntent = Intent(this, FreedomFoodMapsActivity::class.java)
                mapIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFreedomFoodClick(freedomfood: FreedomFoodModel) {
        val launcherIntent = Intent(this, FreedomFoodActivity::class.java)
        launcherIntent.putExtra("freedomfood_edit", freedomfood)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadFreedomFoods() }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {  }
    }

    private fun loadFreedomFoods() {
        showFreedomFoodss(app.freedomfoods.findAll())
    }

    fun showFreedomFoodss (freedomfoods: List<FreedomFoodModel>) {
        binding.recyclerView.adapter = FreedomFoodAdapter(freedomfoods, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}