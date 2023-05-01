package com.example.businessapp.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(

    @field:SerializedName("reviews")
    val reviews: List<ReviewsItem>
)

data class User(

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String
)

data class ReviewsItem(

    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("time_created")
    val timeCreated: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("text")
    val text: String,

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("url")
    val url: String
)
