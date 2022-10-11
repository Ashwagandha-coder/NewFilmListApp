package com.example.newfilmlistapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newfilmlistapp.local.db.Room
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.paging.MoviePopularPagingSource
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryAPI
import kotlinx.coroutines.launch


class FavoritesViewModel() : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<List<MovieDetailWrapper>> = MutableLiveData()
    val favorite = mutableLiveData_favorite


    private val listData = Pager(PagingConfig(pageSize = 20)) {
        MoviePopularPagingSource(ImplRepositoryAPI())
    }.flow.cachedIn(viewModelScope)
    val getListData = listData


    fun getMovie() {

        viewModelScope.launch {

            try {

                mutableLiveData_favorite.value = Room.room.movieDao().getMovieList()


            }
            catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    fun getMovies() {}

}