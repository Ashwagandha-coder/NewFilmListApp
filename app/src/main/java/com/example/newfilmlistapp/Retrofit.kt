package com.example.newfilmlistapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofit {

    private val retrofit_main: retrofit2.Retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val retrofit = retrofit_main


}