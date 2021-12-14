package org.wit.freedomfood_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.freedomfood_android.databinding.CardFreedomfoodBinding
import org.wit.freedomfood_android.models.FreedomFoodModel

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