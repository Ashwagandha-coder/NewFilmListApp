package com.example.newfilmlistapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newfilmlistapp.local.db.Room
import com.example.newfilmlistapp.paging.MovieFavoritePagingSource
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryRoom


class FavoritesViewModel : ViewModel() {


    private val listData = Pager(PagingConfig(pageSize = 20)) {
        MovieFavoritePagingSource(ImplRepositoryRoom(Room.room.movieDao()))
    }.flow.cachedIn(viewModelScope)
    val getListData = listData


}