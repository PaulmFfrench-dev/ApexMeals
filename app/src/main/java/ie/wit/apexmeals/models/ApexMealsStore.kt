package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData

interface ApexMealsStore {
    fun findAll(apexmealsList: MutableLiveData<List<ApexMealsModel>>)
    fun findById(id: String) : ApexMealsModel?
    fun create(apexmeal: ApexMealsModel)
    fun delete(id: String)
}