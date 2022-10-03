package com.example.newfilmlistapp.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.newfilmlistapp.model.PopularWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.example.newfilmlistapp.network.Retrofit
import com.example.newfilmlistapp.paging.MoviePopularPagingSource
import kotlinx.coroutines.launch

class ViewModel_Popular: ViewModel() {


    private val mutableLiveData_popularMovie: MutableLiveData<PopularWrapper> = MutableLiveData()
    val popularMovie = mutableLiveData_popularMovie


    fun requestPopular() {

        viewModelScope.launch {

            try {

                mutableLiveData_popularMovie.value = getPopularMovie()!!

            }

            catch (e: Exception) {
                Log.d(ViewModel_Popular::class.java.name,"request Error - Popular Movie")
                e.printStackTrace()
            }

        }


    }

    fun testPaginationRequestPopular() {

        viewModelScope.launch {




        }


    }

    suspend fun getPopularMovie(): PopularWrapper {

        val loadingMovieDBService = Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getPopularMovie()

        Log.d(ViewModel_Popular::class.java.name,"request OK - Popular Movie")

        return result

    }

    suspend fun testGetPopularMovie() {

        val popularMovie = Pager(PagingConfig(pageSize = 1)) {

            MoviePopularPagingSource()

        }



    }





}