package com.example.newfilmlistapp.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newfilmlistapp.local.MovieDao
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom


@Database(entities = [MovieDetailWrapperRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao



}