package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData
import ie.wit.apexmeals.api.ApexMealsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

object ApexMealsManager : ApexMealsStore {

    val apexmeals = ArrayList<ApexMealsModel>()

    override fun findAll(donationsList: MutableLiveData<List<ApexMealsModel>>) {

        val call = ApexMealsClient.getApi().getall()

        call.enqueue(object : Callback<List<ApexMealsModel>> {
            override fun onResponse(call: Call<List<ApexMealsModel>>,
                                    response: Response<List<ApexMealsModel>>
            ) {
                donationsList.value = response.body() as ArrayList<ApexMealsModel>
                Timber.i("Retrofit JSON = ${response.body()}")
            }

            override fun onFailure(call: Call<List<ApexMealsModel>>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

    override fun findById(id:Long) : ApexMealsModel? {
        val foundDonation: ApexMealsModel? = apexmeals.find { it.id == id }
        return foundDonation
    }

    override fun create(apexmeal: ApexMealsModel) {
        apexmeal.id = getId()
        apexmeals.add(apexmeal)
        logAll()
    }

    fun logAll() {
        Timber.v("** Donations List **")
        apexmeals.forEach { Timber.v("Donate ${it}") }
    }
}