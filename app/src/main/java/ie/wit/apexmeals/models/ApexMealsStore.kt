package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData

interface ApexMealsStore {
    fun findAll(donationsList: MutableLiveData<List<ApexMealsModel>>)
    fun findById(id: Long) : ApexMealsModel?
    fun create(donation: ApexMealsModel)
}