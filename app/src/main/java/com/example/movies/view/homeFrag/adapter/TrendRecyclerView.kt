package com.example.movies.view.homeFrag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.TrendItemRecBinding
import com.example.movies.model.dataClasses.TrendEntity

class TrendRecyclerView(private val data: List<TrendEntity>?, private val itemEvent : EventItem) : RecyclerView.Adapter<TrendRecyclerView.TrendRecyclerViewHolder>() {

    lateinit var binding : TrendItemRecBinding


    inner class TrendRecyclerViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindView(moviesList: TrendEntity) {

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500" + data!![adapterPosition].posterPath)
                .into(binding.imgCover)

            itemView.setOnClickListener {

                itemEvent.onItemClickTrend(data , adapterPosition)

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendRecyclerViewHolder {
        binding = TrendItemRecBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TrendRecyclerViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: TrendRecyclerViewHolder, position: Int) {
        holder.bindView(data!![position])
    }

    interface EventItem {

        fun onItemClickTrend(result : List<TrendEntity>, position: Int)

    }

}