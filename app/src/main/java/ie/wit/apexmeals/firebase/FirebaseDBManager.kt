package ie.wit.apexmeals.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.wit.apexmeals.models.ApexMealsModel
import ie.wit.apexmeals.models.ApexMealsStore

object FirebaseDBManager : ApexMealsStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun findAll(apexmealsList: MutableLiveData<List<ApexMealsModel>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, apexmealsList: MutableLiveData<List<ApexMealsModel>>) {
        TODO("Not yet implemented")
    }

    override fun findById(userid: String, apexmealid: String, apexmeal: MutableLiveData<ApexMealsModel>) {
        TODO("Not yet implemented")
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, apexmeal: ApexMealsModel) {
        TODO("Not yet implemented")
    }

    override fun delete(userid: String, apexmealid: String) {
        TODO("Not yet implemented")
    }

    override fun update(userid: String, apexmealid: String, apexmeal: ApexMealsModel) {
        TODO("Not yet implemented")
    }
}