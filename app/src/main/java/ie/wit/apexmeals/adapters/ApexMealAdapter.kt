package ie.wit.apexmeals.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ie.wit.apexmeals.databinding.CardApexMealDonationBinding
import ie.wit.apexmeals.models.ApexMealModel
import ie.wit.apexmeals.utils.customTransformation

interface ApexMealClickListener {
    fun onApexMealClick(apexmeal: ApexMealModel)
}

class ApexMealAdapter constructor(private var apexmeals: ArrayList<ApexMealModel>,
                                  private val listener: ApexMealClickListener,
                                  private val readOnly: Boolean)
    : RecyclerView.Adapter<ApexMealAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardApexMealDonationBinding
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

    inner class MainHolder(val binding : CardApexMealDonationBinding, private val readOnly : Boolean) :
        RecyclerView.ViewHolder(binding.root) {

        val readOnlyRow = readOnly

        fun bind(apexmeal: ApexMealModel, listener: ApexMealClickListener) {
            binding.root.tag = apexmeal
            binding.apexmealdonation = apexmeal

            Picasso.get().load(apexmeal.profilepic.toUri())
                .resize(200, 200)
                .transform(customTransformation())
                .centerCrop()
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onApexMealClick(apexmeal) }
            binding.executePendingBindings()
        }
    }
}