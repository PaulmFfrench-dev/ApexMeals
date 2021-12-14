package org.wit.freedomfood_android.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FreedomFoodModel(var title: String = "",
                            var description: String = "") : Parcelable
