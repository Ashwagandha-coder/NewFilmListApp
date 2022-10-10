package com.example.newfilmlistapp.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newfilmlistapp.model.MovieDetailWrapper


class FavoritesViewModel(private val context: Context) : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<MovieDetailWrapper> = MutableLiveData()
    val favorite = mutableLiveData_favorite

    fun getMovie() {}

    fun deleteMovie() {}



}