package com.example.newfilmlistapp.local.db

import com.example.newfilmlistapp.app.FilmListApp

object Room {


    fun create(): AppDatabase {
        return androidx.room.Room.databaseBuilder(FilmListApp().getContext().applicationContext,AppDatabase::class.java,"Movie DB").build()
    }

}