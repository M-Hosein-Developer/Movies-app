package com.example.movies.viewModel

import androidx.lifecycle.ViewModel
import com.example.movies.model.Repository
import com.example.movies.model.room.MoviesList
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    suspend fun getAllNowPlay(repository: Repository): MoviesList {
        return repository.getAllNowPlay()
    }


    suspend fun getAllPopular(repository: Repository) : MoviesList {
        return repository.getAllPopular()
    }

    suspend fun getAllTopRate(repository: Repository) : MoviesList {
        return repository.getAllTopRate()
    }

    suspend fun getAllUpcoming(repository: Repository) : MoviesList {
        return repository.getAllUpcoming()
    }

    suspend fun getAllTrend(repository: Repository) : MoviesList {
        return repository.getAllTrend()
    }

}