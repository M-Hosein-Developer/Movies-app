package com.example.movies.view.homeFrag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.FilmItemRecBinding
import com.example.movies.model.dataClasses.TopRatedEntity


class TopRatedRecyclerView(private val data: List<TopRatedEntity>?, val itemEvent: ItemEvent) : RecyclerView.Adapter<TopRatedRecyclerView.FilmItemRecyclerViewHolder>() {

    lateinit var binding: FilmItemRecBinding


    inner class FilmItemRecyclerViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(moviesList: TopRatedEntity) {


            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500" + data!![adapterPosition].posterPath)
                .into(binding.imgCover)


            itemView.setOnClickListener {

                itemEvent.onItemClickTopRated(data , adapterPosition)

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemRecyclerViewHolder {
        binding = FilmItemRecBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return FilmItemRecyclerViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: FilmItemRecyclerViewHolder, position: Int) {
        holder.bindView(data!![position])
    }


    interface ItemEvent {
        fun onItemClickTopRated(result: List<TopRatedEntity>, position: Int)
    }


}