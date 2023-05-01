package com.example.businessapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.businessapp.api.ApiConfig
import com.example.businessapp.response.BusinessResponse
import com.example.businessapp.response.BusinessesItem
import com.example.businessapp.response.ReviewResponse
import com.example.businessapp.response.ReviewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listBusiness = MutableLiveData<List<BusinessesItem>>()
    val listBusiness : LiveData<List<BusinessesItem>> = _listBusiness

    private val _listBusinessReview = MutableLiveData<List<ReviewsItem>>()
    val listBusinessReview : LiveData<List<ReviewsItem>> = _listBusinessReview

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError


    companion object{
        private const val TAG = "MainViewModel"
    }

    fun findReview(alias:String){
        val client = ApiConfig.getService().getReviewBusiness(alias)
        client.enqueue(object :Callback<ReviewResponse>{
            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listBusinessReview.value = responseBody.reviews
                    }
                }else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun findBusiness(city:String){
        _isLoading.postValue(true)
        val client = ApiConfig.getService().getBusiness(city)
        client.enqueue(object : Callback<BusinessResponse> {
            override fun onResponse(
                call: Call<BusinessResponse>,
                response: Response<BusinessResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listBusiness.value = responseBody.businesses
                        _isError.postValue(false)
                    }
                }else {
                    _isError.postValue(true)
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BusinessResponse>, t: Throwable) {
                _isLoading.postValue(false)
                _isError.postValue(true)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}