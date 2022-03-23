package ie.wit.apexmeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.apexmeals.R
import ie.wit.apexmeals.databinding.CardDonationBinding
import ie.wit.apexmeals.models.ApexMealsModel
import ie.wit.apexmeals.utils.hideLoader

interface DonationClickListener {
    fun onDonationClick(donation: ApexMealsModel)
}

class ApexMealsAdapter constructor(private var donations: ArrayList<ApexMealsModel>,
                                  private val listener: DonationClickListener)
    : RecyclerView.Adapter<ApexMealsAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDonationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    fun removeAt(position: Int) {
        donations.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val donation = donations[holder.adapterPosition]
        holder.bind(donation,listener)
    }

    override fun getItemCount(): Int = donations.size

    private fun render(donationsList: ArrayList<ApexMealsModel>) {
        donations ->
        donations?.let {
            render(donations as ArrayList<ApexMealsModel>)
            hideLoader(loader)
            checkSwipeRefresh()
    }
    inner class MainHolder(val binding : CardDonationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(donation: ApexMealsModel, listener: DonationClickListener) {
            binding.root.tag = donation._id
            binding.donation = donation
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
            binding.root.setOnClickListener { listener.onDonationClick(donation) }
            binding.executePendingBindings()
        }
    }
}