package com.example.movies.model.apiService

import com.example.movies.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(API_KEY)
    @GET("3/movie/now_playing?language=en-US&page=1")
    suspend fun getAllNowPlay() : ResponseMovies

    @Headers(API_KEY)
    @GET("3/movie/popular?language=en-US&page=1")
    suspend fun getAllPopular() : ResponseMovies

    @Headers(API_KEY)
    @GET("3/movie/top_rated?language=en-US&page=1")
    suspend fun getAllTopRate() : ResponseMovies

    @Headers(API_KEY)
    @GET("3/movie/upcoming?language=en-US&page=1")
    suspend fun getAllUpcoming() : ResponseMovies

    @Headers(API_KEY)
    @GET("3/trending/all/day?language=en-US")
    suspend fun getAllTrend() : ResponseMovies

    @Headers(API_KEY)
    @GET("3/trending/all/day?language=en-US")
    suspend fun getAllExplore() : ResponseMovies

}