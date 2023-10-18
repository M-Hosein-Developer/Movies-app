package com.example.movies.view.homeFrag

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.movies.databinding.FragmentHomeBinding
import com.example.movies.model.Repository
import com.example.movies.model.apiService.ApiService
import com.example.movies.model.room.Result
import com.example.movies.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() , FilmItemRecyclerView.ItemEvent , TrendRecyclerView.EventItem{



    @Inject
    lateinit var apiService: ApiService

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: FilmItemRecyclerView
    lateinit var adapterTrending: TrendRecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        firstRunMovies()
        trendMovies()

    }

    private fun trendMovies() {

        lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){

                val trend = viewModel.getAllTrend(Repository(apiService)).results

                adapterTrending = TrendRecyclerView(trend , this@HomeFragment)
                binding.recyclerTrend.adapter = adapterTrending

            }

        }

    }

    private fun firstRunMovies() {

        nowPlayingData()
        binding.btnNowPlaying.setTextColor(Color.parseColor("#0296E5"))

        binding.btnNowPlaying.setOnClickListener {
            nowPlayingData()

            binding.btnNowPlaying.setTextColor(Color.parseColor("#0296E5"))
            binding.btnPopular.setTextColor(Color.parseColor("#ffffff"))
            binding.btnTopRate.setTextColor(Color.parseColor("#ffffff"))
            binding.btnUpcoming.setTextColor(Color.parseColor("#ffffff"))
        }

        binding.btnPopular.setOnClickListener {
            popularData()

            binding.btnNowPlaying.setTextColor(Color.parseColor("#ffffff"))
            binding.btnPopular.setTextColor(Color.parseColor("#0296E5"))
            binding.btnTopRate.setTextColor(Color.parseColor("#ffffff"))
            binding.btnUpcoming.setTextColor(Color.parseColor("#ffffff"))
        }

        binding.btnTopRate.setOnClickListener {
            topRate()

            binding.btnNowPlaying.setTextColor(Color.parseColor("#ffffff"))
            binding.btnPopular.setTextColor(Color.parseColor("#ffffff"))
            binding.btnTopRate.setTextColor(Color.parseColor("#0296E5"))
            binding.btnUpcoming.setTextColor(Color.parseColor("#ffffff"))
        }

        binding.btnUpcoming.setOnClickListener {
            upcoming()

            binding.btnNowPlaying.setTextColor(Color.parseColor("#ffffff"))
            binding.btnPopular.setTextColor(Color.parseColor("#ffffff"))
            binding.btnTopRate.setTextColor(Color.parseColor("#ffffff"))
            binding.btnUpcoming.setTextColor(Color.parseColor("#0296E5"))
        }

    }

    private fun upcoming() {

        lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){

                val upcoming = viewModel.getAllUpcoming(Repository(apiService)).results

                adapter = FilmItemRecyclerView(upcoming , this@HomeFragment)
                binding.filmRecycler.adapter = adapter

            }

        }

    }

    private fun topRate() {

        lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){

                val topRate = viewModel.getAllTopRate(Repository(apiService)).results

                adapter = FilmItemRecyclerView(topRate , this@HomeFragment)
                binding.filmRecycler.adapter = adapter
            }

        }

    }

    private fun popularData() {


        lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){

                val popular = viewModel.getAllPopular(Repository(apiService)).results

                adapter = FilmItemRecyclerView(popular , this@HomeFragment)
                binding.filmRecycler.adapter = adapter

            }

        }


    }

    private fun nowPlayingData() {


        lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){

                val nowPlaying = viewModel.getAllNowPlay(Repository(apiService)).results

                adapter = FilmItemRecyclerView(nowPlaying , this@HomeFragment)
                binding.filmRecycler.adapter = adapter

            }


        }


    }

    override fun onItemClick(result: List<Result>, position: Int) {

        val bundle = Bundle()
        bundle.putString("background", result[position].backdropPath)
        bundle.putString("poster", result[position].posterPath)
        bundle.putString("title", result[position].title)
        bundle.putString("date", result[position].releaseDate)
        bundle.putString("about", result[position].overview)

        findNavController().navigate(com.example.movies.R.id.action_homeFragment_to_detailFragment , bundle)


    }

    override fun onItemClickTrend(result: List<Result>, position: Int) {

        val bundle = Bundle()
        bundle.putString("background", result[position].backdropPath)
        bundle.putString("poster", result[position].posterPath)
        bundle.putString("title", result[position].title)
        bundle.putString("date", result[position].releaseDate)
        bundle.putString("about", result[position].overview)

        findNavController().navigate(com.example.movies.R.id.action_homeFragment_to_detailFragment , bundle)


    }


}