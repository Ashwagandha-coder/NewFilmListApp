package com.example.newfilmlistapp

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import androidx.compose.ui.text.TextRange
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory;


class SortByDate : androidx.fragment.app.Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {


        for(i in 0..10){

        }

        val genre = Genres(1,"Action")
        Log.d("SortByDate","result: ${genre}")

        super.onCreate(savedInstanceState)





    }
}