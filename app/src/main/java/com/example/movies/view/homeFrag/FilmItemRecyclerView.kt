package com.example.movies.view.homeFrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.FilmItemRecBinding
import com.example.movies.model.room.Result


class FilmItemRecyclerView(private val data: List<Result> , val itemEvent : ItemEvent) : RecyclerView.Adapter<FilmItemRecyclerView.FilmItemRecyclerViewHolder>() {

    lateinit var binding: FilmItemRecBinding


    inner class FilmItemRecyclerViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(moviesList: Result) {


            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500" + data!![adapterPosition].posterPath)
                .into(binding.imgCover)


            itemView.setOnClickListener {

                itemEvent.onItemClick(data , adapterPosition)

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
        fun onItemClick(result: List<Result> , position: Int)
    }


}