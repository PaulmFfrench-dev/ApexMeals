package ie.wit.apexmeals.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import ie.wit.apexmeals.models.ApexMealsModel
import ie.wit.apexmeals.models.ApexMealsStore
import timber.log.Timber

object FirebaseDBManager : ApexMealsStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun findAll(apexmealsList: MutableLiveData<List<ApexMealsModel>>) {
        database.child("apexmeals")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase ApexMeal error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<ApexMealsModel>()
                    val children = snapshot.children
                    children.forEach {
                        val apexmeals = it.getValue(ApexMealsModel::class.java)
                        localList.add(apexmeals!!)
                    }
                    database.child("apexmeals")
                        .removeEventListener(this)

                    apexmealsList.value = localList
                }
            })
    }

    override fun findAll(userid: String, apexmealsList: MutableLiveData<List<ApexMealsModel>>) {

        database.child("user-apexmeals").child(userid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase ApexMeal error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<ApexMealsModel>()
                    val children = snapshot.children
                    children.forEach {
                        val apexmeal = it.getValue(ApexMealsModel::class.java)
                        localList.add(apexmeal!!)
                    }
                    database.child("user-apexmeals").child(userid)
                        .removeEventListener(this)

                    apexmealsList.value = localList
                }
            })
    }

    override fun findById(userid: String, apexmealid: String, apexmeal: MutableLiveData<ApexMealsModel>) {

        database.child("user-apexmeals").child(userid)
            .child(apexmealid).get().addOnSuccessListener {
                apexmeal.value = it.getValue(ApexMealsModel::class.java)
                Timber.i("firebase Got value ${it.value}")
            }.addOnFailureListener{
                Timber.e("firebase Error getting data $it")
            }
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, apexmeal: ApexMealsModel) {
        Timber.i("Firebase DB Reference : $database")

        val uid = firebaseUser.value!!.uid
        val key = database.child("donations").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        apexmeal.uid = key
        val apexmealValues = apexmeal.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/apexmeals/$key"] = apexmealValues
        childAdd["/user-apexmeals/$uid/$key"] = apexmealValues

        database.updateChildren(childAdd)
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