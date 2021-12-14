package org.wit.freedomfood_android.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreedomfoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = FreedomFoodAdapter(app.freedomfoods.findAll(),this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, FreedomFoodActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFreedomFoodClick(freedomfood: FreedomFoodModel) {
        val launcherIntent = Intent(this, FreedomFoodActivity::class.java)
        launcherIntent.putExtra("freedomfood_edit", freedomfood)
        startActivityForResult(launcherIntent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}