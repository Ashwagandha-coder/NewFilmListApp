package com.example.newfilmlistapp.local.db

import android.content.Context

object Room {

    fun create(context: Context): AppDatabase {
        return androidx.room.Room.databaseBuilder(context,AppDatabase::class.java,"Movie_DB").build()
    }

}