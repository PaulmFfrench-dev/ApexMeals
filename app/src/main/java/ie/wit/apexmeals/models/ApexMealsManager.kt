package ie.wit.apexmeals.models

import androidx.lifecycle.MutableLiveData
import ie.wit.apexmeals.api.ApexMealsClient
import ie.wit.apexmeals.api.ApexMealsWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

object ApexMealsManager : ApexMealsStore {

    private var apexmeals = ArrayList<ApexMealsModel>()

    override fun findAll(apexmealsList: MutableLiveData<List<ApexMealsModel>>) {

        val call = ApexMealsClient.getApi().getall()

        call.enqueue(object : Callback<List<ApexMealsModel>> {
            override fun onResponse(call: Call<List<ApexMealsModel>>,
                                    response: Response<List<ApexMealsModel>>
            ) {
                apexmealsList?.value = response.body() as ArrayList<ApexMealsModel>
                Timber.i("Retrofit JSON = ${response.body()}")
            }

            override fun onFailure(call: Call<List<ApexMealsModel>>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

    override fun findAll(email: String, apexmealsList: MutableLiveData<List<ApexMealsModel>>) {

        val call = ApexMealsClient.getApi().findall(email)

        call.enqueue(object : Callback<List<ApexMealsModel>> {
            override fun onResponse(call: Call<List<ApexMealsModel>>,
                                    response: Response<List<ApexMealsModel>>
            ) {
                apexmealsList.value = response.body() as ArrayList<ApexMealsModel>
                Timber.i("Retrofit JSON = ${response.body()}")
            }

            override fun onFailure(call: Call<List<ApexMealsModel>>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

    override fun findById(email: String, id: String, apexmeals: MutableLiveData<ApexMealsModel>)   {

        val call = ApexMealsClient.getApi().get(email,id)

        call.enqueue(object : Callback<ApexMealsModel> {
            override fun onResponse(call: Call<ApexMealsModel>, response: Response<ApexMealsModel>) {
                apexmeals.value = response.body() as ApexMealsModel
                Timber.i("Retrofit findById() = ${response.body()}")
            }

            override fun onFailure(call: Call<ApexMealsModel>, t: Throwable) {
                Timber.i("Retrofit findById() Error : $t.message")
            }
        })
    }

    override fun create(apexmeals: ApexMealsModel) {

        val call = ApexMealsClient.getApi().post(apexmeals.email,apexmeals)

        call.enqueue(object : Callback<ApexMealsWrapper> {
            override fun onResponse(call: Call<ApexMealsWrapper>,
                                    response: Response<ApexMealsWrapper>
            ) {
                val apexmealsWrapper = response.body()
                if (apexmealsWrapper != null) {
                    Timber.i("Retrofit ${apexmealsWrapper.message}")
                    Timber.i("Retrofit ${apexmealsWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<ApexMealsWrapper>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

    override fun delete(id: String) {
        val call = ApexMealsClient.getApi().delete(id)

        call.enqueue(object : Callback<ApexMealsWrapper> {
            override fun onResponse(call: Call<ApexMealsWrapper>,
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

    override fun update(email: String,id: String, donation: ApexMealsModel) {

        val call = ApexMealsClient.getApi().put(email,id,donation)

        call.enqueue(object : Callback<ApexMealsWrapper> {
            override fun onResponse(call: Call<ApexMealsWrapper>,
                                    response: Response<ApexMealsWrapper>
            ) {
                val ApexMealsWrapper = response.body()
                if (ApexMealsWrapper != null) {
                    Timber.i("Retrofit Update ${ApexMealsWrapper.message}")
                    Timber.i("Retrofit Update ${ApexMealsWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<ApexMealsWrapper>, t: Throwable) {
                Timber.i("Retrofit Update Error : $t.message")
            }
        })
    }
}