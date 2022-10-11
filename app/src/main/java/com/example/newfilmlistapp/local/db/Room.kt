package com.example.newfilmlistapp.local.db

import com.example.newfilmlistapp.app.FilmListApp

object Room {

    private val room_main by lazy {  androidx.room.Room.databaseBuilder(FilmListApp().getContext(),AppDatabase::class.java,"Movie DB").build() }
    val room = room_main

}