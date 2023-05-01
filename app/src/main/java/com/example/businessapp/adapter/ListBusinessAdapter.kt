package com.example.businessapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.businessapp.data.Business
import com.example.businessapp.databinding.ListItemBinding


class ListBusinessAdapter(private val context: Context, private val listBusiness:ArrayList<Business>): RecyclerView.Adapter<ListBusinessAdapter.ViewHolder> () {
    private lateinit var  binding : ListItemBinding
    private lateinit var onItemCallback: OnBusinessCLickCallback


    class ViewHolder(val view:ListItemBinding) :RecyclerView.ViewHolder(view.root){
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setPrice(holder,listBusiness[position].price.toString())
        holder.view.titleBusiness.text = listBusiness[position].name
        holder.view.ratingBusiness.text = listBusiness[position].rating.toString()
        Glide.with(context).load(listBusiness[position].imageUrl).into(holder.view.imgBusiness)
        holder.view.card.setOnClickListener {onItemCallback.onBusinessCLicked(listBusiness[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listBusiness.size

    interface OnBusinessCLickCallback{
        fun onBusinessCLicked(data : Business)
    }

    fun setOnItemClickCallback(onBusinessCLickCallback: OnBusinessCLickCallback){
        this.onItemCallback = onBusinessCLickCallback
    }

    private fun setPrice(holder : ViewHolder, price:String){
        when(price.count()){
            1->{
                holder.view.price100.text = "$"
                holder.view.price50.text = "$$$$"
            }
            2->{
                holder.view.price100.text = "$$"
                holder.view.price50.text = "$$$"
            }
            3->{
                holder.view.price100.text = "$$$"
                holder.view.price50.text = "$$"
            }
            4->{
                holder.view.price100.text = "$$$$"
                holder.view.price50.text = "$"
            }
            5->{
            holder.view.price100.text = "$$$$$"
            holder.view.price50.text = ""
        }
        }
    }
}