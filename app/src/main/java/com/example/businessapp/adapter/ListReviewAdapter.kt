package com.example.businessapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.businessapp.data.Business
import com.example.businessapp.data.BusinessReview
import com.example.businessapp.databinding.ListItemBinding
import com.example.businessapp.databinding.ListItemReviewBinding


class ListReviewAdapter(private val context: Context, private val listReview:ArrayList<BusinessReview>): RecyclerView.Adapter<ListReviewAdapter.ViewHolder> () {
    private lateinit var  binding : ListItemReviewBinding


    class ViewHolder(val view:ListItemReviewBinding) :RecyclerView.ViewHolder(view.root){
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = ListItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.username.text = listReview[position].name
        holder.view.textUser.text = listReview[position].text
        holder.view.ratingUser.text = listReview[position].rating.toString()
        Glide.with(context).load(listReview[position].imageUrl).centerCrop().into(holder.view.imgUser)
    }

    override fun getItemCount(): Int = listReview.size

}