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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Observable

class ViewModelTMDB : ViewModel(), ViewModelProvider.Factory {


    var genresWrapper: GenresWrapper? = null
    var movieWrapper: MovieWrapper? = null

    val retrofit: Retrofit by lazy { initRetrofit() }
    val moshi: Moshi by lazy { initMoshi() }
    private val mutableLiveData: MutableLiveData<MovieWrapper> by lazy { MutableLiveData<MovieWrapper>() }


    fun request(): Job = viewModelScope.launch {

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

// todo: Не работает запрос в сеть
// todo: Пытался в main сделать все равно нихера


suspend fun main() {


    val viewModelTMDB = ViewModelTMDB()

//    val retrofit = viewModelTMDB.initRetrofit()
//
//    val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)
//
//    var param: MovieWrapper? = null
//
//    val job = CoroutineScope(Dispatchers.IO).launch {
//
//        val result = loadingMovieDBService.getMovie(primary_release_year = 1954, genres = "18")
//        param = result
//    }

//    val other_param = job.join()


//    val param2 = viewModelTMDB.movieWrapper

    val param = viewModelTMDB.movieWrapper

    println(param?.results?.get(0)?.overview)


    val param2 = viewModelTMDB.genresWrapper?.genres?.get(0)?.id

    println(param2)


}
