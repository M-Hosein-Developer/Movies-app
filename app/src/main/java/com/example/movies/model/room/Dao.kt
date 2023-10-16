package com.example.movies.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


interface Dao {


    @Query("SELECT * FROM MoviesList")
    fun getAllData() : MoviesList

    @Insert
    fun insertAllDAta(moviesList: MoviesList)

}