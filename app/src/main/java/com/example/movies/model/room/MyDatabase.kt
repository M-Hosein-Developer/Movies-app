package com.example.movies.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [Results::class] , version = 1 , exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun doa() : Dao

}