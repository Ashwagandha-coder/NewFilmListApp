package com.example.newfilmlistapp.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.model.MovieDetailWrapper
import kotlinx.coroutines.launch


class FavoritesViewModel() : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<MovieDetailWrapper> = MutableLiveData()
    val favorite = mutableLiveData_favorite


    fun getMovie() {

        viewModelScope.launch {

            try {



            }
            catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    fun getMovies() {}

}