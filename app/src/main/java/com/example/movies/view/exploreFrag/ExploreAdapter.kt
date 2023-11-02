package com.example.movies.view.exploreFrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.ExploreItemRecBinding
import com.example.movies.model.apiService.Result


class ExploreAdapter(private val data : List<Result>, private val itemEventExplore: ItemEventExplore) : RecyclerView.Adapter<ExploreAdapter.ExploreAdapterHolder>() {

    lateinit var binding : ExploreItemRecBinding

    inner class ExploreAdapterHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindView(result: Result) {

            binding.txtTitleExplore.text = data[adapterPosition].title
            binding.txtRate.text = data[adapterPosition].voteAverage.toString()
            binding.txtDate.text = data[adapterPosition].releaseDate

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500" + data[adapterPosition].posterPath)
                .into(binding.imgCoverExplore)


            itemView.setOnClickListener {
                itemEventExplore.onItemClicked(data , adapterPosition)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreAdapterHolder {
        binding = ExploreItemRecBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ExploreAdapterHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ExploreAdapterHolder, position: Int) {
        holder.bindView(data[position])
    }

    interface ItemEventExplore{

        fun onItemClicked(result: List<Result>, position: Int)
    }
}