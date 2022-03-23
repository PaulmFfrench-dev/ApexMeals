package ie.wit.apexmeals.api

import ie.wit.apexmeals.models.ApexMealsModel
import retrofit2.Call
import retrofit2.http.*

interface ApexMealsService {
    @GET("/apexmeals")
    fun findall(): Call<List<ApexMealsModel>>

    @GET("/apexmeals/{email}")
    fun findall(@Path("email") email: String?)
            : Call<List<ApexMealsModel>>

    @GET("/apexmeals/{email}/{id}")
    fun get(@Path("email") email: String?,
            @Path("id") id: String): Call<ApexMealsModel>

    @DELETE("/apexmeals/{email}/{id}")
    fun delete(@Path("email") email: String?,
               @Path("id") id: String): Call<ApexMealsWrapper>

    @POST("/apexmeals/{email}")
    fun post(@Path("email") email: String?,
             @Body donation: ApexMealsModel)
            : Call<ApexMealsWrapper>

    @PUT("/apexmeals/{email}/{id}")
    fun put(@Path("email") email: String?,
            @Path("id") id: String,
            @Body apexmeals: ApexMealsModel
    ): Call<ApexMealsWrapper>
}