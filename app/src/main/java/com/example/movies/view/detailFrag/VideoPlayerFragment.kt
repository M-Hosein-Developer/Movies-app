package com.example.movies.view.detailFrag

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.NonNull
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movies.R
import com.example.movies.databinding.FragmentVideoPlayerBinding
import com.example.movies.viewModel.MainViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoPlayerFragment : Fragment() {

    private lateinit var binding : FragmentVideoPlayerBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var adapter: VideoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVideoPlayerBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoPlayer()

//        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//        requireActivity().window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private fun videoPlayer() {

        val id = arguments?.getInt("id")!!

        lifecycleScope.launch {

            val data = viewModel.getTrailerById(id)
            adapter = VideoAdapter(requireContext() , data)
            binding.videoRecycler.adapter = adapter

        }

    }


}