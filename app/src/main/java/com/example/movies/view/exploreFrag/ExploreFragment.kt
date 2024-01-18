package com.example.movies.view.exploreFrag

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.FragmentExploreBinding
import com.example.movies.model.apiService.ApiService
import com.example.movies.model.apiService.SearchResponse
import com.example.movies.model.room.MoviesDao
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



        binding.txtSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                lifecycleScope.launch {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                        val data = viewModel.getAllExplore(s.toString() , 3).results
                        adapter = ExploreAdapter(data, this@ExploreFragment)
                        binding.ExploreRecyclerview.adapter = adapter
                        binding.ExploreRecyclerview.layoutManager = LinearLayoutManager(context)
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {

                if (s.toString() == "") binding.txt.visibility = View.VISIBLE
                else binding.txt.visibility = View.GONE

            }

        })

    }

    override fun onItemClicked(result: List<SearchResponse.Result>, position: Int) {

        val bundle = Bundle()
        bundle.putInt("id", result[position].id)
        bundle.putString("background", result[position].backdrop_path)
        bundle.putString("poster", result[position].poster_path)
        bundle.putString("title", result[position].title)
        bundle.putString("date", result[position].release_date)
        bundle.putString("about", result[position].overview)

        findNavController().navigate(com.example.movies.R.id.action_exploreFragment_to_detailFragment , bundle)

    }


}