package org.wit.freedomfood_android.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FreedomFoodModel(var id: Long = 0,
                            var title: String = "",
                            var description: String = "") : Parcelable
