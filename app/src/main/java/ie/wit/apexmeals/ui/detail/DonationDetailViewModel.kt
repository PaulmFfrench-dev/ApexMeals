package ie.wit.apexmeals.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.apexmeals.models.ApexMealsManager
import ie.wit.apexmeals.models.ApexMealsModel

class DonationDetailViewModel : ViewModel() {
    private val donation = MutableLiveData<ApexMealsModel>()

    val observableDonation: LiveData<ApexMealsModel>
        get() = donation

    fun getDonation(id: Long) {
        donation.value = ApexMealsManager.findById(id)
    }
}