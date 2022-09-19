package com.example.newfilmlistapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Observable

class ViewModelTMDB : ViewModel() {

    private lateinit var retrofit: Retrofit
    private lateinit var moshi: Moshi

    private lateinit var genresWrapper: GenresWrapper


    fun getMovie() {}

    init {
        viewModelScope.launch {
            getGenres()
        }

        // test

        val observable = Observable()


    }

    suspend fun getGenres(): GenresWrapper {

        initAllField()

        val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)

        val methodResult: GenresWrapper

        val result = loadingMovieDBService.getGenres()
        methodResult = result
        // Log.d("Test","result: ${result.genres}")
        return result
    }


    fun initRetrofit() {

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    }

    fun initMoshi() {

        moshi = Moshi.Builder()
            .build()

    }

    fun initAllField() {

        initMoshi()
        initRetrofit()


    }


}