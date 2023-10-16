package com.example.movies.view.homeFrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.TrendItemRecBinding
import com.example.movies.model.room.MoviesList

class TrendRecyclerView(private val data : ArrayList<MoviesList>) : RecyclerView.Adapter<TrendRecyclerView.TrendRecyclerViewHolder>() {

    lateinit var binding : TrendItemRecBinding

    inner class TrendRecyclerViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindView(moviesList: MoviesList) {

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

}