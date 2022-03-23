package ie.wit.apexmeals.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.apexmeals.models.ApexMealsManager
import ie.wit.apexmeals.models.ApexMealsModel

class ReportViewModel : ViewModel() {

    private val apexmealsList = MutableLiveData<List<ApexMealsModel>>()

    val observableDonationsList: LiveData<List<ApexMealsModel>>
        get() = apexmealsList

    init {
        load()
    }

    fun load() {
        apexmealsList.value = ApexMealsManager.findAll()
    }
}