package com.example.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.movies.R
import com.example.movies.databinding.FilmItemRecBinding
import com.example.movies.databinding.FragmentDetailBinding
import javax.inject.Inject

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater , container, false)

        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val background = arguments?.getString("background")
        val poster = arguments?.getString("poster")
        val title = arguments?.getString("title")
        val date = arguments?.getString("date")
        val about = arguments?.getString("about")


        Glide.with(requireActivity())
            .load("https://image.tmdb.org/t/p/w500" + background)
            .into(binding.imgCoverDetail)


        Glide.with(requireActivity())
            .load("https://image.tmdb.org/t/p/w500" + poster)
            .into(binding.imgPosterDetail)


        binding.txtTitle.text = title
        binding.txtDateDetail.text = date
        binding.txtAbout.text = about



    }


}