package ie.wit.freedomfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.freedomfood.R
import ie.wit.freedomfood.databinding.CardFreedomfoodBinding
import ie.wit.freedomfood.models.FreedomFoodModel

class FreedomFoodAdapter constructor(private var freedomfoods: List<FreedomFoodModel>)
    : RecyclerView.Adapter<FreedomFoodAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardFreedomfoodBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val donation = freedomfoods[holder.adapterPosition]
        holder.bind(donation)
    }

    override fun getItemCount(): Int = freedomfoods.size

    inner class MainHolder(val binding : CardFreedomfoodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(donation: FreedomFoodModel) {
            binding.paymentamount.text = donation.amount.toString()
            binding.paymentmethod.text = donation.paymentmethod
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}