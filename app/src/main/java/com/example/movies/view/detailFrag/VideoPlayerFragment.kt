package com.example.movies.view.detailFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movies.databinding.FragmentVideoPlayerBinding
import com.example.movies.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoPlayerFragment : Fragment() {

    //binding
    private lateinit var binding : FragmentVideoPlayerBinding

    //view model
    private val viewModel: MainViewModel by viewModels()

    //adapter
    lateinit var adapter: VideoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVideoPlayerBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoPlayer()

    }

    private fun videoPlayer() {

        val id = arguments?.getInt("id")

        if (id == null){

            Toast.makeText(requireActivity(), id, Toast.LENGTH_SHORT).show()

        }else{

            lifecycleScope.launch {

                val data = viewModel.getTrailerById(id)
                adapter = VideoAdapter(data)
                binding.videoRecycler.adapter = adapter

            }

        }



    }


}