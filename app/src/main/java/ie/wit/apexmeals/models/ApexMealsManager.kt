package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import ie.wit.apexmeals.api.ApexMealsClient
import ie.wit.apexmeals.api.ApexMealsWrapper
import ie.wit.apexmeals.firebase.FirebaseDBManager.database
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

object ApexMealsManager : ApexMealsStore {

    override fun findAll(donationsList: MutableLiveData<List<ApexMealsModel>>) {

        val call = ApexMealsClient.getApi().findall()

        call.enqueue(object : Callback<List<ApexMealsModel>> {
            override fun onResponse(
                call: Call<List<ApexMealsModel>>,
                response: Response<List<ApexMealsModel>>
            ) {
                donationsList.value = response.body() as ArrayList<ApexMealsModel>
                Timber.i("Retrofit findAll() = ${response.body()}")
            }

            override fun onFailure(call: Call<List<ApexMealsModel>>, t: Throwable) {
                Timber.i("Retrofit findAll() Error : $t.message")
            }
        })
    }

    override fun findAll(email: String, donationsList: MutableLiveData<List<ApexMealsModel>>) {

        val call = ApexMealsClient.getApi().findall(email)

        call.enqueue(object : Callback<List<ApexMealsModel>> {
            override fun onResponse(
                call: Call<List<ApexMealsModel>>,
                response: Response<List<ApexMealsModel>>
            ) {
                donationsList.value = response.body() as ArrayList<ApexMealsModel>
                Timber.i("Retrofit findAll() = ${response.body()}")
            }

            override fun onFailure(call: Call<List<ApexMealsModel>>, t: Throwable) {
                Timber.i("Retrofit findAll() Error : $t.message")
            }
        })
    }

    override fun findById(email: String, id: String, donation: MutableLiveData<ApexMealsModel>) {

        val call = ApexMealsClient.getApi().get(email, id)

        call.enqueue(object : Callback<ApexMealsModel> {
            override fun onResponse(call: Call<ApexMealsModel>, response: Response<ApexMealsModel>) {
                donation.value = response.body() as ApexMealsModel
                Timber.i("Retrofit findById() = ${response.body()}")
            }

            override fun onFailure(call: Call<ApexMealsModel>, t: Throwable) {
                Timber.i("Retrofit findById() Error : $t.message")
            }
        })
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
        val donationValues = apexmeal.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/donations/$key"] = donationValues
        childAdd["/user-donations/$uid/$key"] = donationValues

        database.updateChildren(childAdd)
    }

    override fun delete(email: String, id: String) {

        val call = ApexMealsClient.getApi().delete(email, id)

        call.enqueue(object : Callback<ApexMealsWrapper> {
            override fun onResponse(
                call: Call<ApexMealsWrapper>,
                response: Response<ApexMealsWrapper>
            ) {
                val donationWrapper = response.body()
                if (donationWrapper != null) {
                    Timber.i("Retrofit Delete ${donationWrapper.message}")
                    Timber.i("Retrofit Delete ${donationWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<ApexMealsWrapper>, t: Throwable) {
                Timber.i("Retrofit Delete Error : $t.message")
            }
        })
    }

    override fun update(email: String, id: String, donation: ApexMealsModel) {

        val call = ApexMealsClient.getApi().put(email, id, donation)

        call.enqueue(object : Callback<ApexMealsWrapper> {
            override fun onResponse(
                call: Call<ApexMealsWrapper>,
                response: Response<ApexMealsWrapper>
            ) {
                val donationWrapper = response.body()
                if (donationWrapper != null) {
                    Timber.i("Retrofit Update ${donationWrapper.message}")
                    Timber.i("Retrofit Update ${donationWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<ApexMealsWrapper>, t: Throwable) {
                Timber.i("Retrofit Update Error : $t.message")
            }
        })
    }
}