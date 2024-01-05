package com.example.movies.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.MainRepository
import com.example.movies.model.apiService.ResponseMovies
import com.example.movies.model.apiService.TrailerResponse
import com.example.movies.model.dataClasses.NowPlayingEntity
import com.example.movies.model.dataClasses.PopularEntity
import com.example.movies.model.dataClasses.TopRatedEntity
import com.example.movies.model.dataClasses.TrendEntity
import com.example.movies.model.dataClasses.UpcomingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    //Now Playing live data
    private val _nowPlayingData = MutableLiveData<List<NowPlayingEntity>?>()
    val nowPlayingData: MutableLiveData<List<NowPlayingEntity>?> get() = _nowPlayingData

    //Popular live data
    private val _popularData = MutableLiveData<List<PopularEntity>?>()
    val popularData: MutableLiveData<List<PopularEntity>?> get() = _popularData

    //Top Rated live data
    private val _topRatedData = MutableLiveData<List<TopRatedEntity>?>()
    val topRatedData: MutableLiveData<List<TopRatedEntity>?> get() = _topRatedData

    //Upcoming live data
    private val _upcomingData = MutableLiveData<List<UpcomingEntity>?>()
    val upcomingData: MutableLiveData<List<UpcomingEntity>?> get() = _upcomingData

    //Trend live data
    private val _trendData = MutableLiveData<List<TrendEntity>?>()
    val trendData : MutableLiveData<List<TrendEntity>?> get() = _trendData


    init {
        getAllNowPlay()
        getAllPopular()
        getAllTopRate()
        getAllUpcoming()
        getAllTrend()

    }


    private fun getAllNowPlay() {
        viewModelScope.launch {
            try {
                val nowPlaying = mainRepository.getAllNowPlay()
                _nowPlayingData.value = nowPlaying
            } catch (e: Exception) {
                Log.v("ErrorViewModel", e.message!!)
            }
        }
    }

    private fun getAllPopular() {

        viewModelScope.launch {
            try {
                val popular = mainRepository.getAllPopular()
                _popularData.value = popular
            } catch (e: Exception) {
                Log.v("ErrorViewModel", e.message!!)
            }

        }


    }

    private fun getAllTopRate() {
        viewModelScope.launch {
            try {
                val topRated = mainRepository.getAllTopRate()
                _topRatedData.value = topRated
            } catch (e: Exception) {
                Log.v("ErrorViewModel", e.message!!)
            }
        }
    }

    private fun getAllUpcoming() {
        viewModelScope.launch {
            try {
                val upcoming = mainRepository.getAllUpcoming()
                _upcomingData.value = upcoming
            } catch (e: Exception) {
                Log.v("ErrorViewModel", e.message!!)
            }
        }
    }

    private fun getAllTrend() {
        viewModelScope.launch {
            try {
                val trend = mainRepository.getAllTrend()
                _trendData.value = trend
                Log.v("trendDataRep", trend.toString())
            } catch (e: Exception) {
                Log.v("ErrorViewModel", e.message!!)
            }
        }
    }


    suspend fun getAllExplore(): ResponseMovies {
        return mainRepository.getAllExplore()
    }

    suspend fun getTrailerById(id: Int): List<TrailerResponse.MoviesResult> {
        return mainRepository.getTrailerById(id)
    }

}