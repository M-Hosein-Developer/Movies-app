package com.example.movies.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movies.model.Result

//@Dao
interface Dao {


    @Query("SELECT * FROM Results")
    fun getAllData() : Results

    @Insert
    fun insertAllDAta(moviesList: List<Results>)

}