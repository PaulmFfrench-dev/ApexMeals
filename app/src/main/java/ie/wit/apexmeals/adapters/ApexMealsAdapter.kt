package ie.wit.apexmeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.apexmeals.R
import ie.wit.apexmeals.databinding.CardFreedomfoodBinding
import ie.wit.apexmeals.models.ApexMealsModel

class ApexMealsAdapter constructor(private var apexmeals: List<ApexMealsModel>)
    : RecyclerView.Adapter<ApexMealsAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardFreedomfoodBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val donation = apexmeals[holder.adapterPosition]
        holder.bind(donation)
    }

    override fun getItemCount(): Int = apexmeals.size

    inner class MainHolder(val binding : CardFreedomfoodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(donation: ApexMealsModel) {
            binding.paymentamount.text = donation.amount.toString()
            binding.paymentmethod.text = donation.paymentmethod
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}