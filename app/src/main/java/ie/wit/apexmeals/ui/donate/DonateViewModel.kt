package ie.wit.apexmeals.ui.donate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.wit.apexmeals.firebase.FirebaseDBManager
import ie.wit.apexmeals.firebase.FirebaseImageManager
import ie.wit.apexmeals.models.ApexMealsModel

class DonateViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addDonation(firebaseUser: MutableLiveData<FirebaseUser>,
                    apexmeal: ApexMealsModel) {
        apexmeal.profilepic = FirebaseImageManager.imageUri.value.toString()
        status.value = try {
            //DonationManager.create(donation)
            FirebaseDBManager.create(firebaseUser,apexmeal)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}