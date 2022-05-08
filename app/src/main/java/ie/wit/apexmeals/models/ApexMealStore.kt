package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface ApexMealStore {
    fun findAll(apexmealsList:
                MutableLiveData<List<ApexMealModel>>)
    fun findAll(userid:String,
                apexmealsList:
                MutableLiveData<List<ApexMealModel>>)
    fun findById(userid:String, apexmealid: String,
                 apexmeal: MutableLiveData<ApexMealModel>)
    fun create(firebaseUser: MutableLiveData<FirebaseUser>, apexmeal: ApexMealModel)
    fun delete(userid:String, apexmealid: String)
    fun update(userid:String, apexmealid: String, apexmeal: ApexMealModel)
}