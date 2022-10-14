package com.example.newfilmlistapp.local.db

import android.content.Context
import com.example.newfilmlistapp.app.FilmListApp

object Room {


    fun create(): AppDatabase {
        return androidx.room.Room.databaseBuilder(FilmListApp().baseContext,AppDatabase::class.java,"Movie DB").build()
    }

}