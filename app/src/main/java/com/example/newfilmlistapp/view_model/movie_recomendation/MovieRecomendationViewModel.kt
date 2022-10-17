package com.example.newfilmlistapp.view_model.movie_recomendation

import android.util.Log
import androidx.lifecycle.*
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.repository.RepositoryAPI
import kotlinx.coroutines.launch

class MovieRecomendationViewModel(private val repositoryAPI: RepositoryAPI) : ViewModel(),
    ViewModelProvider.Factory {


    private val mutableLiveDataMovie: MutableLiveData<com.example.newfilmlistapp.model.Result> =
        MutableLiveData()
    val movie: LiveData<com.example.newfilmlistapp.model.Result> = mutableLiveDataMovie

    private val mutableLiveDataGenres: MutableLiveData<GenresWrapper> = MutableLiveData()
    val genres: LiveData<GenresWrapper> = mutableLiveDataGenres


    init {

        requestMovie()

    }


    fun requestGenres() {
        viewModelScope.launch {

            try {

                val variable = repositoryAPI.getGenres()

                mutableLiveDataGenres.value = variable

            } catch (e: Exception) {

                Log.d(MovieRecomendationViewModel::class.java.name, "Error Request -  Genres")
                e.printStackTrace()

            }


        }


    }


    fun requestMovie(year: Int? = null, genre: String? = null) {
        viewModelScope.launch {

            try {
                Log.d(
                    MovieRecomendationViewModel::class.java.name,
                    "year " + year?.javaClass + " " + "genre " + genre?.javaClass + " " + "- In Request Movie"
                )

                val variable = repositoryAPI.getMovie(year, genre)

                val movie = variable.results[randomNumber()]

                mutableLiveDataMovie.value = movie

            } catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name, "Error Request -  Movie")
                e.printStackTrace()
            }

        }
    }


    private fun randomNumber(): Int = (0..19).random()


}



