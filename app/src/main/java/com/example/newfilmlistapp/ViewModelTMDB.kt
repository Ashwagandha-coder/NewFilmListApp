package com.example.newfilmlistapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.Movie
import com.example.newfilmlistapp.model.MovieWrapper
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

    private val retrofit: Retrofit by lazy { initRetrofit() }
    private val moshi: Moshi by lazy { initMoshi() }
    private val movieWrapper: MovieWrapper? = null
    private val mutableLiveData: MutableLiveData<MovieWrapper> by lazy { MutableLiveData<MovieWrapper>().also {



    } }


    fun request(): MovieWrapper? {

        val result: Movie


        CoroutineScope(Dispatchers.IO).launch {

            Log.d(ViewModelTMDB::class.java.name,"Call first coroutine")

            val param1 = getGenres()

        }

        CoroutineScope(Dispatchers.IO).launch {


            Log.d(ViewModelTMDB::class.java.name,"Call second coroutine")

            val param2 = getMovie()


        }


        return null


    }



    fun getInstanceLiveData(): LiveData<MovieWrapper> { return mutableLiveData }


    suspend fun getMovie(): MovieWrapper {


        val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getMovie(primary_release_year = 1954, genres = "18")

        Log.d(ViewModelTMDB::class.java.name,"request OK")

        return result

    }


    suspend fun getGenres(): GenresWrapper {


        val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getGenres()

         Log.d(ViewModelTMDB::class.java.name,"result: ${result.genres}")
        return result
    }


    fun initRetrofit(): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


        return retrofit


    }

    fun initMoshi(): Moshi {

        val moshi = Moshi.Builder()
            .build()

        return moshi

    }

    fun initAllField() {

        initMoshi()
        initRetrofit()


    }


}