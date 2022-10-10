package com.example.newfilmlistapp.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newfilmlistapp.local.MovieDao
import com.example.newfilmlistapp.model.MovieDetailWrapper


@Database(entities = [MovieDetailWrapper::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {

        INSTANCE = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"Movie DB").build()

        return INSTANCE as AppDatabase

    }


}