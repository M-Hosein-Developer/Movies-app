package com.example.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.Repository
import com.example.movies.model.room.MoviesList
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getAllData(repository: Repository): Call<MoviesList> {
        return repository.getAllData()
    }

}