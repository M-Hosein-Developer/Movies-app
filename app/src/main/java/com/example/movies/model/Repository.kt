package com.example.movies.model

import androidx.lifecycle.LiveData
import com.example.movies.model.apiService.ApiService
import com.example.movies.model.room.MoviesList
import retrofit2.Call
import javax.inject.Inject

class Repository@Inject constructor(private val apiService: ApiService) {


//    suspend fun refreshData(){
//        moviesDao.insertAll(apiService.getAllNowPlay())
//    }

    fun getAllData() : Call<MoviesList> {
//        return moviesDao.getAllData()

        return apiService.getAllNowPlay()
    }

}