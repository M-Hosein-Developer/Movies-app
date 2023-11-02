package com.example.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding


    lateinit var background: String
    private lateinit var poster: String
    lateinit var title: String
    private lateinit var date: String
    private lateinit var about: String


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
        bookMarkBtn()


    }

    private fun bookMarkBtn() {

        var bookMark = false

        binding.btnBookmarke.setOnClickListener {

            if (bookMark == false) {

                binding.btnBookmarke.setImageResource(R.drawable.baseline_bookmark_border_24)
                bookMark = true
            } else {
                binding.btnBookmarke.setImageResource(R.drawable.baseline_bookmark_24)
                bookMark = false
            }

        }

    }

    private fun initComponent() {

        background = arguments?.getString("background").toString()
        poster = arguments?.getString("poster").toString()
        title = arguments?.getString("title").toString()
        date = arguments?.getString("date").toString()
        about = arguments?.getString("about").toString()


        glide.load("https://image.tmdb.org/t/p/w500$background")
            .into(binding.imgCoverDetail)


        glide.load("https://image.tmdb.org/t/p/w500$poster")
            .into(binding.imgPosterDetail)


        binding.txtTitle.text = title
        binding.txtDateDetail.text = date
        binding.txtAbout.text = about

    }


}