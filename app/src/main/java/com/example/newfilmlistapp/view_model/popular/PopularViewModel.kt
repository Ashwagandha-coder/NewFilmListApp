package com.example.newfilmlistapp.view_model.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newfilmlistapp.paging.MoviePopularPagingSource
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryAPI

class PopularViewModel: ViewModel() {


    private val listData = Pager(PagingConfig(pageSize = 20)) {
        MoviePopularPagingSource(ImplRepositoryAPI())
    }.flow.cachedIn(viewModelScope)
    val getListData = listData



}