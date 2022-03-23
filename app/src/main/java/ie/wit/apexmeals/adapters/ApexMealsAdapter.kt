package ie.wit.apexmeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.apexmeals.R
import ie.wit.apexmeals.databinding.CardDonationBinding
import ie.wit.apexmeals.models.ApexMealsModel

interface ApexMealsClickListener {
    fun onDonationClick(apexmeal: ApexMealsModel)
}

class ApexMealsAdapter constructor(private var apexmeals: ArrayList<ApexMealsModel>,
                                  private val listener: ApexMealsClickListener)
    : RecyclerView.Adapter<ApexMealsAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDonationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val apexmeal = apexmeals[holder.adapterPosition]
        holder.bind(apexmeal,listener)
    }

    fun removeAt(position: Int) {
        apexmeals.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = apexmeals.size

    inner class MainHolder(val binding : CardDonationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(apexmeal: ApexMealsModel, listener: ApexMealsClickListener) {
            binding.root.tag = apexmeal._id
            binding.donation = apexmeal
            binding.root.tag = apexmeal
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
            binding.root.setOnClickListener { listener.onDonationClick(apexmeal) }
            binding.executePendingBindings()
        }
    }
}