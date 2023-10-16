package com.example.movies.view.homeFrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.movies.databinding.FilmItemRecBinding
import com.example.movies.model.room.MoviesList
import javax.inject.Inject


class FilmItemRecyclerView(private val data : ArrayList<MoviesList>) : RecyclerView.Adapter<FilmItemRecyclerView.FilmItemRecyclerViewHolder>() {

    lateinit var binding: FilmItemRecBinding


    inner class FilmItemRecyclerViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(moviesList: MoviesList) {


            Glide.with(itemView)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bAZFkReuav0fyCVmWXBeUB93nAe.jpg")
                .into(binding.imgCover)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemRecyclerViewHolder {
        binding = FilmItemRecBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return FilmItemRecyclerViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FilmItemRecyclerViewHolder, position: Int) {
        holder.bindView(data[position])
    }


}