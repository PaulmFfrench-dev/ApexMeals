package ie.wit.apexmeals.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApexMealsModel(
    var _id: String = "N/A",
    @SerializedName("paymenttype")
    val paymentmethod: String = "N/A",
    val message: String = "n/a",
    val amount: Int = 0,
    val upvotes: Int = 0,
    var email: String = "johndoe@apexmeals.com") : Parcelable