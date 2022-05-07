package ie.wit.apexmeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.apexmeals.R
import ie.wit.apexmeals.databinding.CardDonationBinding
import ie.wit.apexmeals.models.ApexMealsModel

interface ApexMealsClickListener {
    fun onApexMealClick(apexmeal: ApexMealsModel)
}

class ApexMealsAdapter constructor(private var apexmeals: ArrayList<ApexMealsModel>,
                                  private val listener: ApexMealsClickListener,
                                  private val readOnly: Boolean)
    : RecyclerView.Adapter<ApexMealsAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDonationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding,readOnly)
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

    inner class MainHolder(val binding : CardDonationBinding, private val readOnly : Boolean) :
        RecyclerView.ViewHolder(binding.root) {

        val readOnlyRow = readOnly

        fun bind(apexmeal: ApexMealsModel, listener: ApexMealsClickListener) {
            binding.root.tag = apexmeal
            binding.donation = apexmeal
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
            binding.root.setOnClickListener { listener.onApexMealClick(apexmeal) }
            binding.executePendingBindings()
        }
    }
}