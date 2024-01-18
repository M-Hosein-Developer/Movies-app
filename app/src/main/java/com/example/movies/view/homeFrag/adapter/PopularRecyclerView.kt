package com.example.movies.view.homeFrag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.FilmItemRecBinding
import com.example.movies.model.dataClasses.PopularEntity


class PopularRecyclerView(private val data: List<PopularEntity>?, val itemEventPopular: ItemEvent) : RecyclerView.Adapter<PopularRecyclerView.FilmItemRecyclerViewHolder>() {

    lateinit var binding: FilmItemRecBinding


    inner class FilmItemRecyclerViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(moviesList: PopularEntity) {


            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500" + moviesList.posterPath)
                .into(binding.imgCover)


            itemView.setOnClickListener {

                itemEventPopular.onItemClickPopular(moviesList)

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
        fun onItemClickPopular(result: PopularEntity)
    }


}