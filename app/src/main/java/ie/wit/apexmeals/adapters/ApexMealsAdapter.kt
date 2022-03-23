package ie.wit.apexmeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.apexmeals.R
import ie.wit.apexmeals.databinding.CardDonationBinding
import ie.wit.apexmeals.models.ApexMealsModel

class ApexMealsAdapter constructor(private var apexmeals: List<ApexMealsModel>)
    : RecyclerView.Adapter<ApexMealsAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDonationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    fun bind(donation: ApexMealsModel) {
        //binding.paymentamount.text = donation.amount.toString()
        //binding.paymentmethod.text = donation.paymentmethod

        binding.donation = donation
        binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
        //Include this call to force the bindings to happen immediately
        binding.executePendingBindings()
    }
    
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val apexmeal = apexmeals[holder.adapterPosition]
        holder.bind(apexmeal)
    }

    override fun getItemCount(): Int = apexmeals.size

    inner class MainHolder(val binding : CardDonationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(apexmeal: ApexMealsModel) {
            binding.paymentamount.text = apexmeal.amount.toString()
            binding.paymentmethod.text = apexmeal.paymentmethod
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}