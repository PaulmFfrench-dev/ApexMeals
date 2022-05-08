package ie.wit.apexmeals.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.wit.apexmeals.firebase.FirebaseDBManager
import ie.wit.apexmeals.models.ApexMealModel
import timber.log.Timber
import java.lang.Exception

class ReportViewModel : ViewModel() {

    private val apexmealsList =
        MutableLiveData<List<ApexMealModel>>()

    val observableApexMealsList: LiveData<List<ApexMealModel>>
        get() = apexmealsList

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()

    var readOnly = MutableLiveData(false)

    var searchResults = ArrayList<ApexMealModel>()

    init { load() }

    fun load() {
        try {
            readOnly.value = false
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!,
                apexmealsList)
            Timber.i("Report Load Success : ${apexmealsList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report Load Error : $e.message")
        }
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
            FirebaseDBManager.delete(userid,id)
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }
}