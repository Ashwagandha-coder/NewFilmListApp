package com.example.newfilmlistapp

import android.util.Log
import androidx.lifecycle.*
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

class ViewModelTMDB : ViewModel(), ViewModelProvider.Factory {


    private var genresWrapper: GenresWrapper? = null
    private var movieWrapper: MovieWrapper? = null

    private val retrofit: Retrofit by lazy { initRetrofit() }
    private val moshi: Moshi by lazy { initMoshi() }
    private val mutableLiveData: MutableLiveData<MovieWrapper> by lazy { MutableLiveData<MovieWrapper>() }


    protected fun request() = viewModelScope.launch {

        val genresWrapperRequest = getGenres()
        genresWrapper = genresWrapperRequest

        val movieWrapperRequest = getMovie()
        movieWrapper = movieWrapperRequest


    }

    fun addDataInLiveData() {

        mutableLiveData.also {
            request()
            it.value = movieWrapper
        }


    }


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

    fun getInstanceLiveData(): LiveData<MovieWrapper> { return mutableLiveData }


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



}