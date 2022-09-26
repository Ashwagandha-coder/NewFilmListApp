package com.example.newfilmlistapp

import android.util.Log
import androidx.lifecycle.*
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ViewModelTMDB : ViewModel(), ViewModelProvider.Factory {


    private val retrofit: Retrofit by lazy { initRetrofit() }
    private val moshi: Moshi by lazy { initMoshi() }
    private val mutableLiveData_movie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val movie: LiveData<MovieWrapper> = mutableLiveData_movie
    private val mutableLiveData_genres: MutableLiveData<GenresWrapper> = MutableLiveData()
    private lateinit var genresWrapper: GenresWrapper

    private val mutableLiveData_movie_detail: MutableLiveData<MovieDetailWrapper> = MutableLiveData()
    val movieDetailWrapper: LiveData<MovieDetailWrapper> = mutableLiveData_movie_detail

    private var movie_ID: Int = 0



    fun requestGenres() {

        viewModelScope.launch {

            try {

                mutableLiveData_genres.value = getGenres()!!
            }
            catch (e: Exception) {

                Log.d(ViewModelTMDB::class.java.name,"Error Request Genres")
                e.printStackTrace()
            }


        }


    }




    fun requestMovie(year: Int, index: Int) {
        viewModelScope.launch {

            try {

              //  val string = index.toString()


                genresWrapper = mutableLiveData_genres.value!!

                val variable = getMovie(year, genresWrapper.genres.get(index).toString())!!

                mutableLiveData_movie.value = variable

                movie_ID = variable.results.firstOrNull()?.id?.toInt() ?: 69

            }
            catch (e: Exception) {
                Log.d(ViewModelTMDB::class.java.name,"Error Request -  Movie")
                e.printStackTrace()
            }



        }
    }

    fun requestMovieDetail() {

        viewModelScope.launch {

            try {

                mutableLiveData_movie_detail.value = getMovieDetail(movie_ID)!!


            }

            catch (e: Exception) {
                Log.d(ViewModelTMDB::class.java.name,"Error Request -  Movie Detail")
                e.printStackTrace()
            }


        }





    }




    suspend fun getMovieDetail(movieID: Int): MovieDetailWrapper {

        val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getMovieDetail(movieID)

        Log.d(ViewModelTMDB::class.java.name, "request OK - Movie Detail ")


        return result

    }


    suspend fun getMovie(year: Int, genre: String): MovieWrapper {

        val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getMovie(primary_release_year = year, genres = listOf<String>(genre))

        Log.d(ViewModelTMDB::class.java.name,"request OK - Random Movie ")

        return result

    }


    suspend fun getGenres(): GenresWrapper {


        val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getGenres()

         Log.d(ViewModelTMDB::class.java.name,"result: ${result.genres}")

        return result
    }

    fun getInstanceLiveDataMovie(): LiveData<MovieWrapper> { return mutableLiveData_movie }

    fun getInstanceLiveDataGenres(): LiveData<GenresWrapper> { return mutableLiveData_genres }


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



