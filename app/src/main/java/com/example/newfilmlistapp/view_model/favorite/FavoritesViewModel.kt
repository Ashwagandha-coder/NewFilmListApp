package com.example.newfilmlistapp.view_model.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newfilmlistapp.local.db.AppDatabase
import com.example.newfilmlistapp.paging.MovieFavoritePagingSource
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryRoom
import com.example.newfilmlistapp.repository.RepositoryRoom


class FavoritesViewModel(private var repositoryRoom: RepositoryRoom) : ViewModel() {


    fun listDataFavorite(room: AppDatabase) = Pager(PagingConfig(pageSize = 1)) {
        repositoryRoom = ImplRepositoryRoom(room)
        MovieFavoritePagingSource(repositoryRoom)
    }.flow.cachedIn(viewModelScope)


}