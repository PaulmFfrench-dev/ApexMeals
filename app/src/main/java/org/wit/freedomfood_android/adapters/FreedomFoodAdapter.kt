package org.wit.freedomfood_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.freedomfood_android.databinding.CardFreedomfoodBinding
import org.wit.freedomfood_android.models.FreedomFoodModel

interface FreedomFoodListener {
    fun onFreedomFoodClick(freedomfood: FreedomFoodModel)
}

class FreedomFoodAdapter constructor(private var freedomfoods: List<FreedomFoodModel>,
                                     private val listener: FreedomFoodListener) :
    RecyclerView.Adapter<FreedomFoodAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardFreedomfoodBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val freedomfood = freedomfoods[holder.adapterPosition]
        holder.bind(freedomfood, listener)
    }

    override fun getItemCount(): Int = freedomfoods.size

    class MainHolder(private val binding : CardFreedomfoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(freedomfood: FreedomFoodModel, listener: FreedomFoodListener) {
            binding.freedomfoodTitle.text = freedomfood.title
            binding.description.text = freedomfood.description
            Picasso.get().load(freedomfood.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onFreedomFoodClick(freedomfood) }
        }
    }
}