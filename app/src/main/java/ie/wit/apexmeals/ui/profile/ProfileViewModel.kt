package ie.wit.apexmeals.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.apexmeals.firebase.FirebaseDBManager
import ie.wit.apexmeals.models.ApexMealModel
import timber.log.Timber
import java.lang.Exception

class ProfileViewModel : ViewModel() {
    private val apexmeal = MutableLiveData<ApexMealModel>()

    var observableProfile: LiveData<ApexMealModel>
        get() = apexmeal
        set(value) {apexmeal.value = value.value}

    fun getApexMeal(userid:String, id: String) {
        try {
            //DonationManager.findById(email, id, apexmeal)
            FirebaseDBManager.findById(userid, id, apexmeal)
            Timber.i("Detail getApexMeal() Success : ${
                apexmeal.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getApexMeal() Error : $e.message")
        }
    }

    fun updateProfile(userid:String, id: String,apexmeal: ApexMealModel) {
        try {
            //DonationManager.update(email, id, apexmeal)
            FirebaseDBManager.update(userid, id, apexmeal)
            Timber.i("Detail update() Success : $apexmeal")
        }
        catch (e: Exception) {
            Timber.i("Detail update() Error : $e.message")
        }
    }
}