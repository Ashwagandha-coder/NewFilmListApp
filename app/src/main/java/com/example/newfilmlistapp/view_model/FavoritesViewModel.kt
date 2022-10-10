package com.example.newfilmlistapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.local.db.Room
import com.example.newfilmlistapp.model.MovieDetailWrapper
import kotlinx.coroutines.launch


class FavoritesViewModel() : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<List<MovieDetailWrapper>> = MutableLiveData()
    val favorite = mutableLiveData_favorite


    fun getMovie() {

        viewModelScope.launch {

            try {

                mutableLiveData_favorite.value = Room.room.movieDao().getMovieList()


            }
            catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    fun getMovies() {}

}