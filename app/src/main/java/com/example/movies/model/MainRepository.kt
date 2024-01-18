package com.example.movies.model

import android.util.Log
import com.example.movies.model.apiService.ApiService
import com.example.movies.model.apiService.ResponseMovies
import com.example.movies.model.apiService.SearchResponse
import com.example.movies.model.apiService.TrailerResponse
import com.example.movies.model.dataClasses.NowPlayingEntity
import com.example.movies.model.dataClasses.PopularEntity
import com.example.movies.model.dataClasses.TopRatedEntity
import com.example.movies.model.dataClasses.TrendEntity
import com.example.movies.model.dataClasses.UpcomingEntity
import com.example.movies.model.room.MoviesDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService, private val moviesDao: MoviesDao) {

    //get data from db

    //Now play
    suspend fun getAllNowPlay(): List<NowPlayingEntity> {

        val response = apiService.getAllNowPlay()

        val data = arrayListOf<NowPlayingEntity>()

        for (i in 0 until response.results.size) {
            val result = response.results[i]
            val movieEntity = NowPlayingEntity(
                result.adult,
                result.backdropPath,
                result.id,
                result.originalLanguage,
                result.originalTitle,
                result.overview,
                result.popularity,
                result.posterPath,
                result.releaseDate,
                result.title,
                result.video,
                result.voteAverage,
                result.voteCount
            )
            data.add(movieEntity)
        }

        moviesDao.insertAllNowPlayingData(data)
        return moviesDao.getAllNowPlayingData()
    }

    //Popular
   suspend fun getAllPopular(): List<PopularEntity> {

        val response = apiService.getAllPopular()

        val data = arrayListOf<PopularEntity>()

        for (i in 0 until response.results.size) {
            val result = response.results[i]
            val popularEntity = PopularEntity(
                result.adult,
                result.backdropPath,
                result.id,
                result.originalLanguage,
                result.originalTitle,
                result.overview,
                result.popularity,
                result.posterPath,
                result.releaseDate,
                result.title,
                result.video,
                result.voteAverage,
                result.voteCount
            )
            data.add(popularEntity)
        }

        moviesDao.insertAllPopularData(data)
        return moviesDao.getAllPopularData()
    }

    //Top rate
    suspend fun getAllTopRate(): List<TopRatedEntity> {

        val response = apiService.getAllTopRate()

        val data = arrayListOf<TopRatedEntity>()

        for (i in 0 until response.results.size) {
            val result = response.results[i]
            val topRatedEntity = TopRatedEntity(
                result.adult,
                result.backdropPath,
                result.id,
                result.originalLanguage,
                result.originalTitle,
                result.overview,
                result.popularity,
                result.posterPath,
                result.releaseDate,
                result.title,
                result.video,
                result.voteAverage,
                result.voteCount
            )
            data.add(topRatedEntity)
        }

        moviesDao.insertAllTopRatedData(data)
        return moviesDao.getAllTopRatedData()
    }

    //Upcoming
     suspend fun getAllUpcoming(): List<UpcomingEntity> {

        val response = apiService.getAllUpcoming()

        val data = arrayListOf<UpcomingEntity>()

        for (i in 0 until response.results.size) {
            val result = response.results[i]
            val upcomingEntity = UpcomingEntity(
                result.adult,
                result.backdropPath,
                result.id,
                result.originalLanguage,
                result.originalTitle,
                result.overview,
                result.popularity,
                result.posterPath,
                result.releaseDate,
                result.title,
                result.video,
                result.voteAverage,
                result.voteCount
            )
            data.add(upcomingEntity)
        }

        moviesDao.insertUpcomingData(data)
        return moviesDao.getAllUpcomingData()

    }

    //All trend
    suspend fun getAllTrend(): List<TrendEntity> {

        val response = apiService.getAllTrend()

        Log.v("trendData" , response.toString())

        val data = arrayListOf<TrendEntity>()

        for (i in 0 until response.results.size) {
            val result = response.results[i]
            val trendEntity = TrendEntity(
                result.adult,
                result.backdropPath,
                result.id,
                result.originalLanguage,
                result.originalTitle,
                result.overview,
                result.popularity,
                result.posterPath,
                result.releaseDate,
                result.title,
                result.video,
                result.voteAverage,
                result.voteCount
            )
            data.add(trendEntity)
        }

        Log.v("trendData" , moviesDao.getAllTrendData().toString())

        moviesDao.insertTrendData(data)
        return moviesDao.getAllTrendData()

    }

    //Explore
    suspend fun getAllExplore(searchText : String ,pageNumber : Int): SearchResponse {

        val a = apiService.getAllExplore(searchText , false , "en-US" , pageNumber)

        Log.v("testSearch" , a.results.toString())

        return a
    }

    //Trailer
    suspend fun getTrailerById(id : Int) : List<TrailerResponse.MoviesResult> {
        return apiService.getTrailerById(id).results
    }

}