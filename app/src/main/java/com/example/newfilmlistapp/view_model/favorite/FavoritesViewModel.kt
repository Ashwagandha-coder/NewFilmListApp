package com.example.newfilmlistapp.view_model.favorite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom
import com.example.newfilmlistapp.repository.RepositoryRoom
import com.example.newfilmlistapp.ui.favorites.FavoritesFragment
import kotlinx.coroutines.launch


class FavoritesViewModel(private var repositoryRoom: RepositoryRoom) : ViewModel() {

    private val mutableLiveDataFavorite: MutableLiveData<List<MovieDetailWrapperRoom>> =
        MutableLiveData()
    val favorite = mutableLiveDataFavorite


    fun getFavoriteMovie() {

        viewModelScope.launch {

            try {
                mutableLiveDataFavorite.value = repositoryRoom.getMovieListLocal()
                Log.d(FavoritesViewModel::class.java.name, "request OK - Favorite Movie")
            } catch (e: Exception) {
                Log.d(FavoritesFragment::class.java.name, "Error request - Favorite Movie")
                e.printStackTrace()
            }
        }


    }


}