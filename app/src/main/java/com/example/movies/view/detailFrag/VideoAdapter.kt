package com.example.movies.view.detailFrag

import android.content.Context
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.VideoItemRecBinding
import com.example.movies.model.apiService.TrailerResponse
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import kotlin.jvm.functions.Function0


class VideoAdapter(private val context: Context, private val data : List<TrailerResponse.MoviesResult>) : RecyclerView.Adapter<VideoAdapter.VideoAdapterViewHolder>() {

    lateinit var binding : VideoItemRecBinding

    inner class VideoAdapterViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(moviesResult: TrailerResponse.MoviesResult) {

            binding.videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    val videoId = moviesResult.key
                    youTubePlayer.loadVideo(videoId, 0f)
                    youTubePlayer.pause()
                }
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapterViewHolder {
        binding = VideoItemRecBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return VideoAdapterViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: VideoAdapterViewHolder, position: Int) {
        holder.bindView(data[position])
    }

}