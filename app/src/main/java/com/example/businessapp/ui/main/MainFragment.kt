package com.example.businessapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.businessapp.adapter.ListBusinessAdapter
import com.example.businessapp.adapter.ListReviewAdapter
import com.example.businessapp.data.Business
import com.example.businessapp.data.BusinessReview
import com.example.businessapp.databinding.FragmentMainBinding
import com.example.businessapp.response.BusinessesItem
import com.example.businessapp.response.ReviewsItem
import com.example.businessapp.utils.toHide
import com.example.businessapp.utils.toShow


class MainFragment : Fragment() {
    private val mainViewModel : MainViewModel by viewModels()
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        val view = binding.root

        initRvBusiness()
        initReview()
        mainViewModel.listBusiness.observe(this.viewLifecycleOwner){ businessItem->
            setResponseBusiness(businessItem)
        }
        mainViewModel.isLoading.observe(this.viewLifecycleOwner){
            showLoading(it)
        }
        mainViewModel.isError.observe(this.viewLifecycleOwner){
            isError(it)
        }
        binding.searchView
            .editText
            .setOnEditorActionListener { v, actionId, event ->
                mainViewModel.findBusiness(v.text.toString())

                false
            }

        return view
    }

    private fun initRvBusiness(){
        val layoutManager = GridLayoutManager(this.context,2)
        binding.rvBusiness.layoutManager = layoutManager

    }

    private fun initReview(){
        val listManager = LinearLayoutManager(this.requireContext())
        binding.detail.rvReview.layoutManager = listManager
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.toShow()
            binding.rvBusiness.toHide()
            binding.emptyList.toHide()
        }else{
            binding.progressBar.toHide()
            binding.rvBusiness.toShow()
            binding.emptyList.toHide()
        }
    }

    private fun isError(isError:Boolean) {
        if (isError){
            binding.emptyList.toShow()
            binding.progressBar.toHide()
            binding.rvBusiness.toHide()
        }

    }
    private fun setResponseBusiness(businesses: List<BusinessesItem>) {
        val listBusiness = ArrayList<Business>()
        for(business in businesses){
            Log.i(TAG, "setResponseBusiness: ${business.name}")
            listBusiness.add(
                Business(
                    business.name,
                    business.alias,
                    business.imageUrl,
                    business.price,
                    business.rating,
                    business.reviewCount,
                    business.coordinates.latitude,
                    business.coordinates.longitude,
                    business.isClosed
                )
            )
        }
        val sortingListBusiness = ArrayList(listBusiness.filter { it.isClose == false }.sortedBy { it.name })
        val adapter = ListBusinessAdapter(requireContext(), sortingListBusiness)
        binding.rvBusiness.adapter  = adapter

        adapter.setOnItemClickCallback(object :ListBusinessAdapter.OnBusinessCLickCallback{
            override fun onBusinessCLicked(data: Business) {

                binding.searchView.hide()
                mainViewModel.findReview(data.alias.toString())
                binding.emptyBox.toHide()
                binding.detail.root.visibility = View.VISIBLE
                binding.detail.tvName.text = data.name
                binding.detail.ratingBusiness.text = data.rating.toString()
                binding.detail.reviewCount.text = data.reviewCount.toString()
                binding.detail.tvName.text = data.name
                Glide.with(requireContext()).load(data.imageUrl).into(binding.detail.imgBusinessDetail)
                setPrice(data.price.toString())
                mainViewModel.listBusinessReview.observe(viewLifecycleOwner){
                    setReview(it)
                }

            }

        })
    }

    fun setReview(reviews: List<ReviewsItem>){
        val listBusiness = ArrayList<BusinessReview>()
        for(review in reviews){
            listBusiness.add(
                BusinessReview(
                    review.user.name,
                    review.text,
                    review.user.imageUrl,
                    review.timeCreated,
                    review.rating
                )
            )
        }
        val adapter = ListReviewAdapter(requireContext(), listBusiness)
        binding.detail.rvReview.apply {
            this.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun setPrice(price:String){
        when(price.count()){
            1->{
                binding.detail.price100.text = "$"
                binding.detail.price50.text = "$$$$"
            }
            2->{
                binding.detail.price100.text = "$$"
                binding.detail.price50.text = "$$$"
            }
            3->{
                binding.detail.price100.text = "$$$"
                binding.detail.price50.text = "$$"
            }
            4->{
                binding.detail.price100.text = "$$$$"
                binding.detail.price50.text = "$"
            }
            5->{
                binding.detail.price100.text = "$$$$$"
                binding.detail.price50.text = ""
            }
        }
    }
}
