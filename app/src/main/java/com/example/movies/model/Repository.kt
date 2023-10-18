package com.example.movies.model

import com.example.movies.model.apiService.ApiService
import com.example.movies.model.room.MoviesList
import retrofit2.Call
import javax.inject.Inject

class Repository@Inject constructor(private val apiService: ApiService) {


//    suspend fun refreshData(){
//        moviesDao.insertAll(apiService.getAllNowPlay())
//    }

    suspend fun getAllNowPlay() : MoviesList {
//        return moviesDao.getAllData()

        return apiService.getAllNowPlay()
    }

    suspend fun getAllPopular() : MoviesList {

        return apiService.getAllPopular()
    }

    suspend fun getAllTopRate() : MoviesList {

        return apiService.getAllTopRate()
    }

    suspend fun getAllUpcoming() : MoviesList {

        return apiService.getAllUpcoming()
    }

    suspend fun getAllTrend() : MoviesList {

        return apiService.getAllTrend()
    }

    suspend fun getAllExplore() : MoviesList {

        return apiService.getAllExplore()
    }

}