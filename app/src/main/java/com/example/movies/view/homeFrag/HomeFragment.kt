package com.example.movies.view.homeFrag

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movies.databinding.FragmentHomeBinding
import com.example.movies.model.dataClasses.NowPlayingEntity
import com.example.movies.model.dataClasses.PopularEntity
import com.example.movies.model.dataClasses.TopRatedEntity
import com.example.movies.model.dataClasses.TrendEntity
import com.example.movies.model.dataClasses.UpcomingEntity
import com.example.movies.view.homeFrag.adapter.NowPlayingRecyclerView
import com.example.movies.view.homeFrag.adapter.PopularRecyclerView
import com.example.movies.view.homeFrag.adapter.TopRatedRecyclerView
import com.example.movies.view.homeFrag.adapter.TrendRecyclerView
import com.example.movies.view.homeFrag.adapter.UpcomingRecyclerView
import com.example.movies.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), NowPlayingRecyclerView.ItemEvent, PopularRecyclerView.ItemEvent,
    TopRatedRecyclerView.ItemEvent, UpcomingRecyclerView.ItemEvent, TrendRecyclerView.EventItem {

    //Binding
    lateinit var binding: FragmentHomeBinding

    //adapter
    private lateinit var nowPlayingAdapter: NowPlayingRecyclerView
    private lateinit var popularAdapter: PopularRecyclerView
    private lateinit var topRatedAdapter: TopRatedRecyclerView
    private lateinit var upcomingAdapter: UpcomingRecyclerView
    private lateinit var adapterTrending: TrendRecyclerView

    //view model
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitViews
        firstRunMovies()
        trendMovies()
        loadingAnimation()
    }

    // Loading animation
    private fun loadingAnimation() {
        binding.loading.playAnimation()
        binding.loading1.playAnimation()
    }

    // Trend Movies - Top of screen recycler View
    private fun trendMovies() {

        viewModel.trendData.observe(viewLifecycleOwner) { movie ->

            Log.v("trendData", movie.toString())

            adapterTrending = TrendRecyclerView(movie, this@HomeFragment)
            binding.recyclerTrend.adapter = adapterTrending
            binding.loading.visibility = View.GONE

        }

    }

    //First run and handle ui and button
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

    //Up coming data and set to recycler
    private fun upcoming() {

        viewModel.upcomingData.observe(viewLifecycleOwner) { movie ->

            upcomingAdapter = UpcomingRecyclerView(movie, this@HomeFragment)
            binding.filmRecycler.adapter = upcomingAdapter
            binding.loading1.visibility = View.GONE
        }

    }

    //Top rate data and set to recycler
    private fun topRate() {

        viewModel.topRatedData.observe(viewLifecycleOwner) { movie ->

            topRatedAdapter = TopRatedRecyclerView(movie, this@HomeFragment)
            binding.filmRecycler.adapter = topRatedAdapter
            binding.loading1.visibility = View.GONE

        }

    }

    //Popular data and set to recycler
    private fun popularData() {

        viewModel.popularData.observe(viewLifecycleOwner) { movie ->

            Log.v("dataTest", movie.toString())
            popularAdapter = PopularRecyclerView(movie, this@HomeFragment)
            binding.filmRecycler.adapter = popularAdapter
            binding.loading1.visibility = View.GONE
        }

    }

    // Now playing data and set to recycler
    private fun nowPlayingData() {

        viewModel.nowPlayingData.observe(viewLifecycleOwner) { movie ->

            nowPlayingAdapter = NowPlayingRecyclerView(movie, this@HomeFragment)
            binding.filmRecycler.adapter = nowPlayingAdapter
            binding.loading1.visibility = View.GONE

        }
    }

    // Send data to detail fragment
    override fun onItemClick(result: NowPlayingEntity) {

        val bundle = Bundle()
        bundle.putInt("id", result.id)
        bundle.putString("background", result.backdropPath)
        bundle.putString("poster", result.posterPath)
        bundle.putString("title", result.title)
        bundle.putString("date", result.releaseDate)
        bundle.putString("about", result.overview)

        findNavController().navigate(
            com.example.movies.R.id.action_homeFragment_to_detailFragment,
            bundle
        )


    }

    override fun onItemClickTrend(result: TrendEntity) {

        val bundle = Bundle()
        bundle.putInt("id", result.id)
        bundle.putString("background", result.backdropPath)
        bundle.putString("poster", result.posterPath)
        bundle.putString("title", result.title)
        bundle.putString("date", result.releaseDate)
        bundle.putString("about", result.overview)

        findNavController().navigate(
            com.example.movies.R.id.action_homeFragment_to_detailFragment,
            bundle
        )

    }

    override fun onItemClickPopular(result: PopularEntity) {

        val bundle = Bundle()
        bundle.putInt("id", result.id)
        bundle.putString("background", result.backdropPath)
        bundle.putString("poster", result.posterPath)
        bundle.putString("title", result.title)
        bundle.putString("date", result.releaseDate)
        bundle.putString("about", result.overview)

        findNavController().navigate(
            com.example.movies.R.id.action_homeFragment_to_detailFragment,
            bundle
        )

    }

    override fun onItemClickTopRated(result: TopRatedEntity) {

        val bundle = Bundle()
        bundle.putInt("id", result.id)
        bundle.putString("background", result.backdropPath)
        bundle.putString("poster", result.posterPath)
        bundle.putString("title", result.title)
        bundle.putString("date", result.releaseDate)
        bundle.putString("about", result.overview)

        findNavController().navigate(
            com.example.movies.R.id.action_homeFragment_to_detailFragment,
            bundle
        )

    }

    override fun onItemClickUpcoming(result: UpcomingEntity) {

        val bundle = Bundle()
        bundle.putInt("id", result.id)
        bundle.putString("background", result.backdropPath)
        bundle.putString("poster", result.posterPath)
        bundle.putString("title", result.title)
        bundle.putString("date", result.releaseDate)
        bundle.putString("about", result.overview)

        findNavController().navigate(
            com.example.movies.R.id.action_homeFragment_to_detailFragment,
            bundle
        )

    }

}