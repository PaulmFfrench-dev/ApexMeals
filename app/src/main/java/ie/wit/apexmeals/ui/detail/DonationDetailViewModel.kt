package ie.wit.apexmeals.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.apexmeals.models.ApexMealsManager
import ie.wit.apexmeals.models.ApexMealsModel
import timber.log.Timber

class DonationDetailViewModel : ViewModel() {
    private val donation = MutableLiveData<ApexMealsModel>()

    var observableDonation: LiveData<ApexMealsModel>
        get() = donation
        set(value) {donation.value = value.value}

    fun getDonation(email:String, id: String) {
        try {
            ApexMealsManager.findById(email, id, donation)
            Timber.i("Detail getDonation() Success : ${donation.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getDonation() Error : $e.message")
        }
    }

    fun updateDonation(email:String, id: String,donation: ApexMealsModel) {
        try {
            ApexMealsManager.update(email, id, donation)
            Timber.i("Detail update() Success : $donation")
        }
        catch (e: Exception) {
            Timber.i("Detail update() Error : $e.message")
        }
    }
}