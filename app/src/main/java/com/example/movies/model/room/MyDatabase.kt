package com.example.movies.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [MoviesList::class] , version = 1 , exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun doa() : Dao

}