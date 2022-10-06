package com.example.newfilmlistapp.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.example.newfilmlistapp.network.Retrofit
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {

    private val mutableLiveData_movie_detail: MutableLiveData<MovieDetailWrapper> = MutableLiveData()
    val movieDetailWrapper: LiveData<MovieDetailWrapper> = mutableLiveData_movie_detail

    // init room




    fun loadData() {

        viewModelScope.launch {




        }


    }





    fun requestMovieDetail(id: Int) {

        viewModelScope.launch {

            try {


                // Movie Detail
                Log.d(MovieDetailViewModel::class.java.name,"Значение movieID before request - $id") // todo: вставить значение movieID

                mutableLiveData_movie_detail.value = getMovieDetail(id.toString())!!


            }

            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Значение movieID before request with error - $id")
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request -  Movie Detail")
                e.printStackTrace()
            }


        }

    }

    suspend fun getMovieDetail(movieID: String): MovieDetailWrapper {

        val loadingMovieDBService = Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getMovieDetail(movieID)

        Log.d(MovieRecomendationViewModel::class.java.name, "request OK - Movie Detail ")


        return result

    }

}