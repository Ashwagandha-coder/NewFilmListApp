package com.example.newfilmlistapp.local.db

import android.content.Context
import com.example.newfilmlistapp.app.FilmListApp

object Room {


    fun create(context: Context): AppDatabase {
        return androidx.room.Room.databaseBuilder(context,AppDatabase::class.java,"Movie DB").build()
    }

}