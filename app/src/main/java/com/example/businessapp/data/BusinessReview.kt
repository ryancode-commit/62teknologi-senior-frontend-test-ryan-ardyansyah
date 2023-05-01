package com.example.businessapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BusinessReview(
    var name :String? = null,
    var text :String? = null,
    var imageUrl :String? = null,
    var time :String? = null,
    var rating :Int? = null,
): Parcelable
