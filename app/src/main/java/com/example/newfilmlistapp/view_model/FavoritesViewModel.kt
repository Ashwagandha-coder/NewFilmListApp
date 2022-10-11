package com.example.newfilmlistapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.paging.MovieFavoritePagingSource
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryRoom


class FavoritesViewModel() : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<List<MovieDetailWrapper>> = MutableLiveData()
    val favorite = mutableLiveData_favorite


    private val listData = Pager(PagingConfig(pageSize = 20)) {
        MovieFavoritePagingSource(ImplRepositoryRoom())
    }.flow.cachedIn(viewModelScope)
    val getListData = listData



}