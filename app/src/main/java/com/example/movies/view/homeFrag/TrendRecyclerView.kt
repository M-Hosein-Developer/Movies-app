package com.example.movies.view.homeFrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewDebug.IntToString
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.movies.databinding.TrendItemRecBinding
import com.example.movies.model.room.Result
import javax.inject.Inject

class TrendRecyclerView(private val data: List<Result> , private val itemEvent : EventItem) : RecyclerView.Adapter<TrendRecyclerView.TrendRecyclerViewHolder>() {

    lateinit var binding : TrendItemRecBinding


    inner class TrendRecyclerViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindView(moviesList: Result) {

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
        return data.size
    }

    override fun onBindViewHolder(holder: TrendRecyclerViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    interface EventItem {

        fun onItemClickTrend(result : List<Result> , position: Int)

    }

}