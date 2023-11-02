package com.example.movies.view.exploreFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.FragmentExploreBinding
import com.example.movies.model.apiService.ApiService
import com.example.movies.model.room.MoviesDao
import com.example.movies.model.apiService.Result
import com.example.movies.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() , ExploreAdapter.ItemEventExplore {

    lateinit var binding : FragmentExploreBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: ExploreAdapter

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var roomDao : MoviesDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentExploreBinding.inflate(layoutInflater , container , false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        getAllData()

    }

    private fun getAllData() {

        lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {

                val data = viewModel.getAllExplore().results

                adapter = ExploreAdapter(data , this@ExploreFragment)
                binding.ExploreRecyclerview.adapter = adapter
                binding.ExploreRecyclerview.layoutManager = LinearLayoutManager(context)

            }

        }

    }

    override fun onItemClicked(result: List<Result>, position: Int) {
        val bundle = Bundle()
        bundle.putString("background", result[position].backdropPath)
        bundle.putString("poster", result[position].posterPath)
        bundle.putString("title", result[position].title)
        bundle.putString("date", result[position].releaseDate)
        bundle.putString("about", result[position].overview)

        findNavController().navigate(com.example.movies.R.id.action_exploreFragment_to_detailFragment , bundle)

    }


}