package com.example.newfilmlistapp.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.model.SaveDataBackScreen
import com.example.newfilmlistapp.network.LoadingMovieDBService
import kotlinx.coroutines.launch
import kotlin.random.Random

class ViewModel_SortByDate : ViewModel(), ViewModelProvider.Factory {


    private val mutableLiveData_movie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val movie: LiveData<MovieWrapper> = mutableLiveData_movie
    private val mutableLiveData_genres: MutableLiveData<GenresWrapper> = MutableLiveData()
    val genres: LiveData<GenresWrapper> = mutableLiveData_genres

//    private val mutableLiveData_saveData: MutableLiveData<SaveDataBackScreen> = MutableLiveData()
//    val saveData: LiveData<SaveDataBackScreen> = mutableLiveData_saveData

    private lateinit var genresWrapper: GenresWrapper

    private lateinit var movie_ID: String

    private var number_random: Int = 0
    val array_index = number_random


    private var year_index: Int = 0
    val getYaerIndex = year_index
    private var genre_index: Int = 0
    val getGenreIndex = genre_index





    fun requestGenres() {

        viewModelScope.launch {

            try {

                mutableLiveData_genres.value = getGenres()!!

            }
            catch (e: Exception) {

                Log.d(ViewModel_SortByDate::class.java.name,"Error Request -  Genres")
                e.printStackTrace()
            }


        }


    }




    fun requestMovie(year: Int, index: Int) {
        viewModelScope.launch {

            try {



                genresWrapper = mutableLiveData_genres.value!!

                val variable = getMovie(year, genresWrapper.genres.get(index).toString())!!

                mutableLiveData_movie.value = variable

                number_random = Random.Default.nextInt(0,9)

                Log.d(ViewModel_SortByDate::class.java.name,"Random number - $number_random")

                movie_ID = variable.results.get(number_random)?.id.toString() ?: ""

                Log.d(ViewModel_SortByDate::class.java.name,movie_ID + " " + "Значение movie_ID")


                year_index = year
                genre_index = index

//                mutableLiveData_saveData.value = SaveDataBackScreen(year,index,variable.results.get(index).posterPath,variable)

            }
            catch (e: Exception) {
                Log.d(ViewModel_SortByDate::class.java.name,"Error Request -  Movie")
                e.printStackTrace()
            }



        }
    }


    suspend fun getMovie(year: Int, genre: String): MovieWrapper {

        val loadingMovieDBService = com.example.newfilmlistapp.network.Retrofit.retrofit.create(LoadingMovieDBService::class.java) // todo: Нарушение Dry Нужно вынести в синглетон

        val result = loadingMovieDBService.getMovie(primary_release_year = year, genres = listOf<String>(genre))

        Log.d(ViewModel_SortByDate::class.java.name,"request OK - Random Movie ")

        return result

    }


    suspend fun getGenres(): GenresWrapper {


        val loadingMovieDBService = com.example.newfilmlistapp.network.Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getGenres()

         Log.d(ViewModel_SortByDate::class.java.name,"request OK - Genres")

        return result
    }






}



