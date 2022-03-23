package ie.wit.apexmeals.ui.donate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.apexmeals.models.ApexMealsManager
import ie.wit.apexmeals.models.ApexMealsModel

class DonateViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addDonation(donation: ApexMealsModel) {
        status.value = try {
            ApexMealsManager.create(donation)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}