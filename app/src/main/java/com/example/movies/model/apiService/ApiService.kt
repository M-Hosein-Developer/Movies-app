package com.example.movies.model.apiService

import API_KEY
import com.example.movies.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(API_KEY)
    @GET("3/movie/now_playing?language=en-US&page=1")
    suspend fun getAllNowPlay() : MoviesList

    @Headers(API_KEY)
    @GET("3/movie/popular?language=en-US&page=1")
    suspend fun getAllPopular() : MoviesList

    @Headers(API_KEY)
    @GET("3/movie/top_rated?language=en-US&page=1")
    suspend fun getAllTopRate() : MoviesList

    @Headers(API_KEY)
    @GET("3/movie/upcoming?language=en-US&page=1")
    suspend fun getAllUpcoming() : MoviesList

    @Headers(API_KEY)
    @GET("3/trending/all/day?language=en-US")
    suspend fun getAllTrend() : MoviesList

    @Headers(API_KEY)
    @GET("3/trending/all/day?language=en-US")
    suspend fun getAllExplore() : MoviesList

}