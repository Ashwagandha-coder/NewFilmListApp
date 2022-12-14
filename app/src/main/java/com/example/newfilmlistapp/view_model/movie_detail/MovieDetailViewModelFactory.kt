package com.example.newfilmlistapp.view_model.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newfilmlistapp.repository.RepositoryAPI
import com.example.newfilmlistapp.repository.RepositoryRoom

class MovieDetailViewModelFactory(private val repositoryAPI: RepositoryAPI, private val repositoryRoom: RepositoryRoom) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(repositoryAPI,repositoryRoom) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}