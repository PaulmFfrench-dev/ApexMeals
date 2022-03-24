package ie.wit.apexmeals.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.wit.apexmeals.models.ApexMealsModel
import ie.wit.apexmeals.models.ApexMealsStore
import timber.log.Timber

object FirebaseDBManager : ApexMealsStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun findAll(apexmealsList: MutableLiveData<List<ApexMealsModel>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, apexmealsList: MutableLiveData<List<ApexMealsModel>>) {
        TODO("Not yet implemented")
    }

    override fun findById(userid: String, apexmealid: String, apexmeal: MutableLiveData<ApexMealsModel>) {

        database.child("user-donations").child(userid)
            .child(apexmealid).get().addOnSuccessListener {
                apexmeal.value = it.getValue(ApexMealsModel::class.java)
                Timber.i("firebase Got value ${it.value}")
            }.addOnFailureListener{
                Timber.e("firebase Error getting data $it")
            }
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, apexmeal: ApexMealsModel) {
        TODO("Not yet implemented")
    }

    override fun delete(userid: String, apexmealid: String) {

        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/apexmeals/$apexmealid"] = null
        childDelete["/user-apexmeals/$userid/$apexmealid"] = null

        database.updateChildren(childDelete)
    }

    override fun update(userid: String, apexmealid: String, apexmeal: ApexMealsModel) {

        val apexmealValues = apexmeal.toMap()

        val childUpdate : MutableMap<String, Any?> = HashMap()
        childUpdate["apexmeals/$apexmealid"] = apexmealValues
        childUpdate["user-apexmeals/$userid/$apexmealid"] = apexmealValues

        database.updateChildren(childUpdate)
    }
}