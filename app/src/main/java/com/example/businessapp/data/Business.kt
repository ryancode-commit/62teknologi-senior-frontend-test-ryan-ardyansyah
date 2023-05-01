package com.example.businessapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Business(
    var name :String? = null,
    var alias :String? = null,
    var imageUrl :String? = null,
    var price :String? = null,
    var rating :Double? = null,
    var reviewCount :Int? = null,
    var latitude : Double? = null,
    var longitude : Double? = null,
    var isClose : Boolean? = false,
): Parcelable
