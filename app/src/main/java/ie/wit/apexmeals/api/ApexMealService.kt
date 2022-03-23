package ie.wit.apexmeals.api

import ie.wit.apexmeals.models.ApexMealsModel
import retrofit2.Call
import retrofit2.http.*

interface DonationService {
    @GET("/apexmeals")
    fun getall(): Call<List<ApexMealsModel>>

    @GET("/apexmeals/{id}")
    fun get(@Path("id") id: String): Call<ApexMealsModel>

    @DELETE("/apexmeals/{id}")
    fun delete(@Path("id") id: String): Call<ApexMealsWrapper>

    @POST("/apexmeals")
    fun post(@Body donation: ApexMealsModel): Call<ApexMealsWrapper>

    @PUT("/apexmeals/{id}")
    fun put(@Path("id") id: String,
            @Body donation: ApexMealsModel
    ): Call<ApexMealsWrapper>
}