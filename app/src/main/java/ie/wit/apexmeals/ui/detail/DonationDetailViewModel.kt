package ie.wit.apexmeals.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.apexmeals.firebase.FirebaseDBManager
import ie.wit.apexmeals.models.ApexMealsModel
import timber.log.Timber
import java.lang.Exception

class DonationDetailViewModel : ViewModel() {
    private val apexmeal = MutableLiveData<ApexMealsModel>()

    var observableDonation: LiveData<ApexMealsModel>
        get() = apexmeal
        set(value) {apexmeal.value = value.value}

    fun getDonation(userid:String, id: String) {
        try {
            //DonationManager.findById(email, id, donation)
            FirebaseDBManager.findById(userid, id, apexmeal)
            Timber.i("Detail getDonation() Success : ${
                apexmeal.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getDonation() Error : $e.message")
        }
    }

    fun updateDonation(userid:String, id: String,apexmeal: ApexMealsModel) {
        try {
            //DonationManager.update(email, id, donation)
            FirebaseDBManager.update(userid, id, apexmeal)
            Timber.i("Detail update() Success : $apexmeal")
        }
        catch (e: Exception) {
            Timber.i("Detail update() Error : $e.message")
        }
    }
}