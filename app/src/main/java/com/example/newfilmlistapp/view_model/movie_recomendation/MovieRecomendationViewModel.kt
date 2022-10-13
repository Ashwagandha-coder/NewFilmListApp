package com.example.newfilmlistapp.view_model.movie_recomendation

import android.util.Log
import androidx.lifecycle.*
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.repository.RepositoryAPI
import kotlinx.coroutines.launch

class MovieRecomendationViewModel(private val repositoryAPI: RepositoryAPI) : ViewModel(), ViewModelProvider.Factory {


    private val mutableLiveDataMovie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val movie: LiveData<MovieWrapper> = mutableLiveDataMovie

    private val mutableLiveDataGenres: MutableLiveData<GenresWrapper> = MutableLiveData()
    val genres: LiveData<GenresWrapper> = mutableLiveDataGenres

    private val mutableLiveDataDefaultMovie: MutableLiveData<MovieWrapper> = MutableLiveData()
    val defaultMovie: LiveData<MovieWrapper> = mutableLiveDataDefaultMovie



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


    fun requestMovie(year: Int, genre: String) {
        viewModelScope.launch {

            try {
                Log.d(MovieRecomendationViewModel::class.java.name,"year " + year.javaClass + " " + "genre " + genre.javaClass + " " + "- In Request Movie")

                val variable = repositoryAPI.getMovie(year, genre)

                mutableLiveDataMovie.value = variable

            }
            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request -  Movie")
                e.printStackTrace()
            }


        }
    }


}



