package com.example.newfilmlistapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newfilmlistapp.model.PopularWrapper
import com.example.newfilmlistapp.repository.RepositoryForRoom

class FavoritesViewModel(private val repositoryForRoom: RepositoryForRoom) : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<PopularWrapper> = MutableLiveData()
    val favorite = mutableLiveData_favorite



    private fun onLoad() {



    }


}