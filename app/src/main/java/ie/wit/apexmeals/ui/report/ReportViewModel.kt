package ie.wit.apexmeals.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.apexmeals.models.ApexMealsManager
import ie.wit.apexmeals.models.ApexMealsModel
import timber.log.Timber
import java.lang.Exception

class ReportViewModel : ViewModel() {

    private val donationsList =
        MutableLiveData<List<ApexMealsModel>>()

    val observableDonationsList: LiveData<List<ApexMealsModel>>
        get() = donationsList

    init { load() }

    fun load() {
        try {
            ApexMealsManager.findAll(donationsList)
            Timber.i("Retrofit Load Success : $donationsList.value")
        }
        catch (e: Exception) {
            Timber.i("Retrofit Load Error : $e.message")
        }
    }

    fun delete(id: String) {
        try {
            ApexMealsManager.delete(id)
            Timber.i("Retrofit Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Retrofit Delete Error : $e.message")
        }
    }
}