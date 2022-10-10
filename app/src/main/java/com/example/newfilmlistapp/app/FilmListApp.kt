package com.example.newfilmlistapp.app

import android.app.Application
import com.example.newfilmlistapp.local.db.AppDatabase

class FilmListApp: Application() {

    fun getContext(): FilmListApp { return this }

}