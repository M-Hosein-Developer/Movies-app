package com.example.movies.di.module

import com.example.movies.utils.MOVIES_DATABASE
import android.content.Context
import androidx.room.Room
import com.example.movies.model.room.MoviesDao
import com.example.movies.model.room.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : MyDatabase{
        return Room.databaseBuilder(context , MyDatabase::class.java , MOVIES_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideDao(database: MyDatabase) : MoviesDao{
        return database.movieDao()
    }

}