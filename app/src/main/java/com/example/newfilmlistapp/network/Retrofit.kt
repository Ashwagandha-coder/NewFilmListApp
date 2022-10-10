package com.example.newfilmlistapp.network

import com.example.newfilmlistapp.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofit {

    private val retrofit_main: retrofit2.Retrofit by lazy {

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        retrofit2.Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val retrofit = retrofit_main


}