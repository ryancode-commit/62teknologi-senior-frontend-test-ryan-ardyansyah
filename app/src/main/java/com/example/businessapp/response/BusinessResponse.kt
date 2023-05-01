package com.example.businessapp.response

import com.google.gson.annotations.SerializedName

data class BusinessResponse(

	@field:SerializedName("businesses")
	val businesses: List<BusinessesItem>
)

data class Coordinates(

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("longitude")
	val longitude: Double
)

data class BusinessesItem(

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("coordinates")
	val coordinates: Coordinates,

	@field:SerializedName("alias")
	val alias: String,

	@field:SerializedName("review_count")
	val reviewCount: Int,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("is_closed")
	val isClosed: Boolean
)
