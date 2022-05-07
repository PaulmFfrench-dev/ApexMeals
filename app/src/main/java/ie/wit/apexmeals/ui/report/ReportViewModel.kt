package ie.wit.apexmeals.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.wit.apexmeals.firebase.FirebaseDBManager
import ie.wit.apexmeals.models.ApexMealsModel
import timber.log.Timber
import java.lang.Exception

class ReportViewModel : ViewModel() {

    private val apexmealsList =
        MutableLiveData<List<ApexMealsModel>>()

    val observableApexMealsList: LiveData<List<ApexMealsModel>>
        get() = apexmealsList

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()
    var readOnly = MutableLiveData(false)

    init { load() }

    fun load() {
        try {
            //DonationManager.findAll(liveFirebaseUser.value?.email!!, donationsList)
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!,
                apexmealsList)
            Timber.i("Report Load Success : ${apexmealsList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report Load Error : $e.message")
        }
        readOnly.value = false
    }

    fun loadAll() {
        try {
            readOnly.value = true
            FirebaseDBManager.findAll(apexmealsList)
            Timber.i("Report LoadAll Success : ${apexmealsList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report LoadAll Error : $e.message")
        }
    }

    fun delete(userid: String, id: String) {
        try {
            //DonationManager.delete(userid,id)
            FirebaseDBManager.delete(userid,id)
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }
}