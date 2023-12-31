package com.example.movies.view.detailFrag

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        initComponent()
        bookMarkBtn()



    }


    private fun bookMarkBtn() {

        var bookMark = false

        binding.btnBookmarke.setOnClickListener {

            bookMark = if (!bookMark) {

                binding.btnBookmarke.setImageResource(R.drawable.baseline_bookmark_border_24)
                true
            } else {
                binding.btnBookmarke.setImageResource(R.drawable.baseline_bookmark_24)
                false
            }

        }

    }

    private fun initComponent() {

        val id = arguments?.getInt("id")!!
        background = arguments?.getString("background").toString()
        poster = arguments?.getString("poster").toString()
        title = arguments?.getString("title").toString()
        date = arguments?.getString("date").toString()
        about = arguments?.getString("about").toString()


        trailer(id)

        glide.load("https://image.tmdb.org/t/p/w500$background")
            .into(binding.imgCoverDetail)


        glide.load("https://image.tmdb.org/t/p/w500$poster")
            .into(binding.imgPosterDetail)


        binding.txtTitle.text = title
        binding.txtDateDetail.text = date
        binding.txtAbout.text = about

    }

    private fun trailer(id: Int) {


        binding.btnPlayVideo.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", id)
            findNavController().navigate(R.id.action_detailFragment_to_videoPlayerFragment , bundle)
        }

    }


}