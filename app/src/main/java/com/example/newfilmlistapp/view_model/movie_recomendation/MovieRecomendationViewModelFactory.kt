package com.example.newfilmlistapp.view_model.movie_recomendation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newfilmlistapp.repository.RepositoryAPI

class MovieRecomendationViewModelFactory(private val repositoryAPI: RepositoryAPI) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MovieRecomendationViewModel::class.java)) {
            return MovieRecomendationViewModel(repositoryAPI) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}