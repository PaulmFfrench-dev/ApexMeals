package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface ApexMealsStore {
    fun findAll(apexMealsList:
                MutableLiveData<List<ApexMealsModel>>)
    fun findAll(userid:String,
                apexMealsList:
                MutableLiveData<List<ApexMealsModel>>)
    fun findById(userid:String, apexmealid: String,
                 apexmeal: MutableLiveData<ApexMealsModel>)
    fun create(firebaseUser: MutableLiveData<FirebaseUser>, apexmeal: ApexMealsModel)
    fun delete(userid:String, apexmealid: String)
    fun update(userid:String, apexmealid: String, apexmeal: ApexMealsModel)
}