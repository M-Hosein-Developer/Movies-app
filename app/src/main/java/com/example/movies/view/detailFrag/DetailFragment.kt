package com.example.movies.view.detailFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //biding
    lateinit var binding: FragmentDetailBinding


    lateinit var background: String
    private lateinit var poster: String
    lateinit var title: String
    private lateinit var date: String
    private lateinit var about: String

    //glide Injection
    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent()

    }

    //Init ui
    private fun initComponent() {

        //get data
        val id = arguments?.getInt("id")!!
        background = arguments?.getString("background").toString()
        poster = arguments?.getString("poster").toString()
        title = arguments?.getString("title").toString()
        date = arguments?.getString("date").toString()
        about = arguments?.getString("about").toString()

        //trailer func
        trailer(id)

        //init cover and textview
        glide.load("https://image.tmdb.org/t/p/w500$background")
            .into(binding.imgCoverDetail)

        glide.load("https://image.tmdb.org/t/p/w500$poster")
            .into(binding.imgPosterDetail)

        binding.txtTitle.text = title
        binding.txtDateDetail.text = date
        binding.txtAbout.text = about

    }

    //trailer func
    private fun trailer(id: Int) {

        binding.btnPlayVideo.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", id)
            findNavController().navigate(R.id.action_detailFragment_to_videoPlayerFragment , bundle)
        }

    }


}