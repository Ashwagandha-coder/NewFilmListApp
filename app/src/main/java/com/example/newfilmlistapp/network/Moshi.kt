package com.example.newfilmlistapp.network

object Moshi {

    private val moshi_main by lazy {
        com.squareup.moshi.Moshi.Builder()
            .build()
    }
    val moshi = moshi_main


}