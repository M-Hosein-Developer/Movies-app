package com.example.movies.model.apiService

import API_KEY
import com.example.movies.model.room.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(API_KEY)
    @GET("3/movie/now_playing?language=en-US&page=1")
    fun getAllNowPlay() : Call<MoviesList>

}