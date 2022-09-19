package com.example.newfilmlistapp

import androidx.lifecycle.ViewModel
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class ViewModelTMDB : ViewModel() {

    private lateinit var retrofit: Retrofit
    private lateinit var moshi: Moshi



    fun getMovie() {}

    fun getGenres(): GenresWrapper? {

        initAllField()

        val loadingMovieDBService =  retrofit.create(LoadingMovieDBService::class.java)


        CoroutineScope(Dispatchers.IO).launch {

            var methodResult: GenresWrapper

            try {
                val result = loadingMovieDBService.getGenres()
                methodResult = result
               // Log.d("Test","result: ${result.genres}")

            } catch (e: Exception){
               // Log.e("error", "fetch movie error $e")
            }


        }

        return null
    }


    fun initRetrofit() {}

    fun initMoshi() {}

    fun initAllField() {}


}