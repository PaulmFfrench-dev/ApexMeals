package ie.wit.apexmeals.ui.donate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.wit.apexmeals.firebase.FirebaseDBManager
import ie.wit.apexmeals.firebase.FirebaseImageManager
import ie.wit.apexmeals.models.ApexMealModel

class ApexMealDonateViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addApexMealDonation(firebaseUser: MutableLiveData<FirebaseUser>,
                    apexmeal: ApexMealModel) {
        status.value = try {
            apexmeal.profilepic = FirebaseImageManager.imageUri.value.toString()
            FirebaseDBManager.create(firebaseUser,apexmeal)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}