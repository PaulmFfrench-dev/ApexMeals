package ie.wit.apexmeals.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class ApexMealModel(
    var uid: String? = "",
    var paymentmethod: String = "N/A",
    var amount: Int = 0,
    var note: String = "Keep up the good work!",
    var profilepic: String = "",
    var name: String = "",
    var address: String = "",
    var phoneno: Int = 0,
    var email: String? = "JD@gmail.com",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0)

    : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "paymentmethod" to paymentmethod,
            "amount" to amount,
            "note" to note,
            "profilepic" to profilepic,
            "name" to name,
            "address" to address,
            "phoneno" to phoneno,
            "email" to email,
            "latitude" to latitude,
            "longitude" to longitude
        )
    }
}