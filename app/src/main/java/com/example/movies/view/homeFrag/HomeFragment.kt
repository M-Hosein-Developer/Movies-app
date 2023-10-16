package com.example.movies.view.homeFrag

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.movies.R
import com.example.movies.databinding.FragmentHomeBinding
import com.example.movies.model.Repository
import com.example.movies.model.apiService.ApiService
import com.example.movies.model.room.MoviesList
import com.example.movies.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var viewModel : MainViewModel

    @Inject
    lateinit var apiService: ApiService

    lateinit var adapter : FilmItemRecyclerView
    lateinit var adapterTrending : TrendRecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater , container , false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)



        viewModel.getAllData(Repository(apiService)).enqueue(object : Callback<MoviesList> {
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                val array = ArrayList<MoviesList>()
                array.add(response.body()!!)

                adapter = FilmItemRecyclerView(array)
                binding.filmRecycler.adapter = adapter

            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {

            }

        })





    }

}