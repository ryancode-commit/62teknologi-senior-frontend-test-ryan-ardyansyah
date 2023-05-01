package com.example.businessapp.api

import com.example.businessapp.response.BusinessResponse
import com.example.businessapp.response.ReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search?sort_by=best_match&limit=20")
    fun getBusiness(
        @Query("location") city: String
    ): Call<BusinessResponse>


    @GET("{alias}/reviews?limit=20&sort_by=yelp_sort")
    fun getReviewBusiness(
        @Path("alias") alias:String
    ): Call<ReviewResponse>
}