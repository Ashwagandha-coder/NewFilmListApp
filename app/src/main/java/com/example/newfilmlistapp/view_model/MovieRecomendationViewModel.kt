package com.example.newfilmlistapp.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import kotlinx.coroutines.launch

class MovieRecomendationViewModel : ViewModel(), ViewModelProvider.Factory {


    private val mutableLiveData_movie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val movie: LiveData<MovieWrapper> = mutableLiveData_movie
    private val mutableLiveData_genres: MutableLiveData<GenresWrapper> = MutableLiveData()
    val genres: LiveData<GenresWrapper> = mutableLiveData_genres

    // Default Movie

    private val mutableLiveData_default_movie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val default_movie: LiveData<MovieWrapper> = mutableLiveData_default_movie



    fun requestGenres() {

        viewModelScope.launch {

            try {

                mutableLiveData_genres.value = getGenres()!!

            }
            catch (e: Exception) {

                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request -  Genres")
                e.printStackTrace()
            }


        }


    }


    fun requestDefaultMovie() {

        viewModelScope.launch {

            try {

                val variable = getDefaultMovie()

                mutableLiveData_default_movie.value = variable

            }
            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request - Default Movie")
                e.printStackTrace()
            }



        }


    }


    fun requestMovie(year: Int, genre: String) {
        viewModelScope.launch {

            try {

                Log.d(MovieRecomendationViewModel::class.java.name,"year " + year.javaClass + " " + "genre " + genre.javaClass + " " + "- In Request Movie")

                val variable = getMovie(year, genre)

                mutableLiveData_movie.value = variable


            }
            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request -  Movie")
                e.printStackTrace()
            }



        }
    }


    suspend fun getMovie(year: Int, genre: String): MovieWrapper {

        val loadingMovieDBService = com.example.newfilmlistapp.network.Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getMovie(primary_release_year = year, genres = listOf(genre))

        Log.d(MovieRecomendationViewModel::class.java.name,"request OK - Random Movie ")

        return result

    }


    suspend fun getGenres(): GenresWrapper {


        val loadingMovieDBService = com.example.newfilmlistapp.network.Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getGenres()

         Log.d(MovieRecomendationViewModel::class.java.name,"request OK - Genres")

        return result
    }


    suspend fun getDefaultMovie(): MovieWrapper {

        val loadingMovieDBService = com.example.newfilmlistapp.network.Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getDefaultMovie()

        Log.d(MovieRecomendationViewModel::class.java.name,"request OK - Default Movie ")

        return result


    }






}



