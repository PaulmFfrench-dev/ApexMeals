package ie.wit.apexmeals.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApexMealsModel(var id: Long = 0,
                         @SerializedName("paymenttype")
                         val paymentmethod: String = "N/A",
                         val amount: Int = 0) : Parcelable