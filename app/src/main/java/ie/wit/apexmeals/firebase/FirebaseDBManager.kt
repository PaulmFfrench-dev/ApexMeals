package ie.wit.apexmeals.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import ie.wit.apexmeals.models.ApexMealModel
import ie.wit.apexmeals.models.ApexMealStore
import timber.log.Timber

object FirebaseDBManager : ApexMealStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun findAll(apexmealsList: MutableLiveData<List<ApexMealModel>>) {
        database.child("apexmeals")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Donation error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<ApexMealModel>()
                    val children = snapshot.children
                    children.forEach {
                        val apexmeal = it.getValue(ApexMealModel::class.java)
                        localList.add(apexmeal!!)
                    }
                    database.child("apexmeals")
                        .removeEventListener(this)

                    apexmealsList.value = localList
                }
            })
    }

    override fun findAll(userid: String, apexmealsList: MutableLiveData<List<ApexMealModel>>) {

        database.child("user-apexmeals").child(userid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Apex Meal error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<ApexMealModel>()
                    val children = snapshot.children
                    children.forEach {
                        val apexmeal = it.getValue(ApexMealModel::class.java)
                        localList.add(apexmeal!!)
                    }
                    database.child("user-apexmeals").child(userid)
                        .removeEventListener(this)

                    apexmealsList.value = localList
                }
            })
    }

    override fun findById(userid: String, apexmealid: String, apexmeal: MutableLiveData<ApexMealModel>) {

        database.child("user-apexmeals").child(userid)
            .child(apexmealid).get().addOnSuccessListener {
                apexmeal.value = it.getValue(ApexMealModel::class.java)
                Timber.i("firebase Got value ${it.value}")
            }.addOnFailureListener{
                Timber.e("firebase Error getting data $it")
            }
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, apexmeal: ApexMealModel) {
        Timber.i("Firebase DB Reference : $database")

        val uid = firebaseUser.value!!.uid
        val key = database.child("apexmeals").push().key
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

    override fun update(userid: String, apexmealid: String, apexmeal: ApexMealModel) {

        val apexmealValues = apexmeal.toMap()

        val childUpdate : MutableMap<String, Any?> = HashMap()
        childUpdate["apexmeals/$apexmealid"] = apexmealValues
        childUpdate["user-apexmeals/$userid/$apexmealid"] = apexmealValues

        database.updateChildren(childUpdate)
    }

    fun updateImageRef(userid: String,imageUri: String) {

        val userApexMeals = database.child("user-apexmeals").child(userid)
        val allApexMeals = database.child("apexmeals")

        userApexMeals.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        //Update Users imageUri
                        it.ref.child("profilepic").setValue(imageUri)
                        //Update all donations that match 'it'
                        val apexmeal = it.getValue(ApexMealModel::class.java)
                        allApexMeals.child(apexmeal!!.uid!!)
                            .child("profilepic").setValue(imageUri)
                    }
                }
            })
    }
}