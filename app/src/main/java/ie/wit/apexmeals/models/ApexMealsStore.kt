package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData

interface ApexMealsStore {
    fun findAll(apexmealsList:
                MutableLiveData<List<ApexMealsModel>>)
    fun findAll(email: String, apexmealsList:
    MutableLiveData<List<ApexMealsModel>>)
    fun findById(id: String) : ApexMealsModel?
    fun create(apexmeals: ApexMealsModel)
    fun delete(email: String,id: String)
}