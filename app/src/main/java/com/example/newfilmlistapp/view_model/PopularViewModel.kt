package com.example.newfilmlistapp.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newfilmlistapp.model.PopularWrapper
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.example.newfilmlistapp.network.Retrofit
import com.example.newfilmlistapp.paging.MoviePopularPagingSource
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryAPI
import kotlinx.coroutines.launch

class PopularViewModel: ViewModel() {


    private val mutableLiveData_popularMovie: MutableLiveData<PopularWrapper> = MutableLiveData()
    val popularMovie = mutableLiveData_popularMovie

    private val mutableLiveData_pager: MutableLiveData<Pager<Int,ResultPopular>> = MutableLiveData()
    val pager = mutableLiveData_pager


    private val listData = Pager(PagingConfig(pageSize = 7)) {

        MoviePopularPagingSource(ImplRepositoryAPI())

    }.flow.cachedIn(viewModelScope)
    val getListData = listData


    fun requestPopular() {

        viewModelScope.launch {

            try {

                mutableLiveData_popularMovie.value = getPopularMovie()!!

            }

            catch (e: Exception) {
                Log.d(PopularViewModel::class.java.name,"request Error - Popular Movie")
                e.printStackTrace()
            }

        }


    }


    suspend fun getPopularMovie(): PopularWrapper {

        val loadingMovieDBService = Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getPopularMovie()

        Log.d(PopularViewModel::class.java.name,"request OK - Popular Movie")

        return result

    }


}