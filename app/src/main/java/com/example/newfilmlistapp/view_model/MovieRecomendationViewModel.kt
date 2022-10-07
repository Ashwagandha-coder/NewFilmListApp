package com.example.newfilmlistapp.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import kotlinx.coroutines.launch
import kotlin.random.Random

class MovieRecomendationViewModel : ViewModel(), ViewModelProvider.Factory {


    private val mutableLiveData_movie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val movie: LiveData<MovieWrapper> = mutableLiveData_movie
    private val mutableLiveData_genres: MutableLiveData<GenresWrapper> = MutableLiveData()
    val genres: LiveData<GenresWrapper> = mutableLiveData_genres

    // Default Movie

    private val mutableLiveData_default_movie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val default_movie: LiveData<MovieWrapper> = mutableLiveData_default_movie

//    private val mutableLiveData_saveData: MutableLiveData<SaveDataBackScreen> = MutableLiveData()
//    val saveData: LiveData<SaveDataBackScreen> = mutableLiveData_saveData

    private lateinit var genresWrapper: GenresWrapper

    private lateinit var movie_ID: String

    private var number_random: Int = 0
    val array_index = number_random


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

                number_random = Random.Default.nextInt(0,9)

                Log.d(MovieRecomendationViewModel::class.java.name,"Random number - $number_random")

                movie_ID = variable.results.get(number_random)?.id.toString() ?: ""

                Log.d(MovieRecomendationViewModel::class.java.name,movie_ID + " " + "Значение movie_ID")


//                mutableLiveData_saveData.value = SaveDataBackScreen(year,index,variable.results.get(index).posterPath,variable)

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

                val variable = getMovie(year, genre)!!

                mutableLiveData_movie.value = variable

                number_random = Random.Default.nextInt(0,9)

                Log.d(MovieRecomendationViewModel::class.java.name,"Random number - $number_random")

                movie_ID = variable.results.get(number_random)?.id.toString() ?: ""

                Log.d(MovieRecomendationViewModel::class.java.name,movie_ID + " " + "Значение movie_ID")


//                mutableLiveData_saveData.value = SaveDataBackScreen(year,index,variable.results.get(index).posterPath,variable)

            }
            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request -  Movie")
                e.printStackTrace()
            }



        }
    }


    suspend fun getMovie(year: Int, genre: String): MovieWrapper {

        val loadingMovieDBService = com.example.newfilmlistapp.network.Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getMovie(primary_release_year = year, genres = listOf<String>(genre))

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



