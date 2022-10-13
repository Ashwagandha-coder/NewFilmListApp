package com.example.newfilmlistapp.view_model.movie_recomendation

import android.util.Log
import androidx.lifecycle.*
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.repository.RepositoryAPI
import kotlinx.coroutines.launch

class MovieRecomendationViewModel(private val repositoryAPI: RepositoryAPI) : ViewModel(), ViewModelProvider.Factory {


    private val mutableLiveDataMovie: MutableLiveData<com.example.newfilmlistapp.model.Result> = MutableLiveData()
    val movie: LiveData<com.example.newfilmlistapp.model.Result> = mutableLiveDataMovie

    private val mutableLiveDataGenres: MutableLiveData<GenresWrapper> = MutableLiveData()
    val genres: LiveData<GenresWrapper> = mutableLiveDataGenres

    private val mutableLiveDataDefaultMovie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val defaultMovie: LiveData<MovieWrapper> = mutableLiveDataDefaultMovie


    init {

        requestMovie(4,"99")

    }


    fun requestGenres() {

        viewModelScope.launch {

            try {

                val variable = repositoryAPI.getGenres()

                mutableLiveDataGenres.value = variable

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

                val variable = repositoryAPI.getDefaultMovie()

                mutableLiveDataDefaultMovie.value = variable

            }
            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request - Default Movie")
                e.printStackTrace()
            }

        }

    }


    fun requestMovie(year: Int = 2018, genre: String = "18") {
        viewModelScope.launch {

            try {
                Log.d(MovieRecomendationViewModel::class.java.name,"year " + year.javaClass + " " + "genre " + genre.javaClass + " " + "- In Request Movie")

                val variable = repositoryAPI.getMovie(year, genre)

                val movie = variable.results[randomNumber()]

                mutableLiveDataMovie.value = movie

            }
            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request -  Movie")
                e.printStackTrace()
            }

        }
    }


    private fun randomNumber(): Int = (0..19).random()


}



