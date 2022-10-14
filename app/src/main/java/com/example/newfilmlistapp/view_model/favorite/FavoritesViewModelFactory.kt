package com.example.newfilmlistapp.view_model.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newfilmlistapp.repository.RepositoryRoom

class FavoritesViewModelFactory(private val repositoryRoom: RepositoryRoom) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(repositoryRoom) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}