package org.wit.freedomfood_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.freedomfood_android.R
import org.wit.freedomfood_android.databinding.ActivityFreedomfoodListBinding
import org.wit.freedomfood_android.databinding.CardFreedomfoodBinding
import org.wit.freedomfood_android.main.MainApp
import org.wit.freedomfood_android.models.FreedomFoodModel

class FreedomFoodListActivity : AppCompatActivity() {

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
            binding.recyclerView.adapter = FreedomFoodAdapter(app.freedomfoods)
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    }

    class FreedomFoodAdapter constructor(private var freedomfoods: List<FreedomFoodModel>) :
        RecyclerView.Adapter<FreedomFoodAdapter.MainHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            val binding = CardFreedomfoodBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return MainHolder(binding)
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            val freedomfood = freedomfoods[holder.adapterPosition]
            holder.bind(freedomfood)
        }

        override fun getItemCount(): Int = freedomfoods.size

        class MainHolder(private val binding : CardFreedomfoodBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(freedomfood: FreedomFoodModel) {
                binding.freedomfoodTitle.text = freedomfood.title
                binding.description.text = freedomfood.description
            }
        }
    }