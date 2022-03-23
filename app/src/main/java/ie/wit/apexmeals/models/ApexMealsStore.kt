package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData

interface ApexMealsStore {
    fun findAll(apexmealsList:
                MutableLiveData<List<ApexMealsModel>>)
    fun findAll(email: String, apexmealsList:
    MutableLiveData<List<ApexMealsModel>>)
    fun findById(email:String, id: String, apexmeals: MutableLiveData<ApexMealsModel>)
    fun create(apexmeals: ApexMealsModel)
    fun delete(email: String,id: String)
    fun update(email:String,id: String,donation: ApexMealsModel)
}