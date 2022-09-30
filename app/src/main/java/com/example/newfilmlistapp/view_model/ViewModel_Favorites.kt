package com.example.newfilmlistapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newfilmlistapp.model.ResultPopular

class ViewModel_Favorites : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<ResultPopular> = MutableLiveData()
    val favorite = mutableLiveData_favorite


}