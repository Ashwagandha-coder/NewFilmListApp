package com.example.newfilmlistapp.view_model.movie_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.local.db.Room
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom
import com.example.newfilmlistapp.repository.RepositoryAPI
import com.example.newfilmlistapp.view_model.movie_recomendation.MovieRecomendationViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repositoryAPI: RepositoryAPI): ViewModel() {

    private val mutableLiveDataMovieDetail: MutableLiveData<MovieDetailWrapper> = MutableLiveData()
    val movieDetailWrapper: LiveData<MovieDetailWrapper> = mutableLiveDataMovieDetail

    private val mutableLiveDataRoom: MutableLiveData<MovieDetailWrapper> = MutableLiveData()
    val roomMovie: LiveData<MovieDetailWrapper> = mutableLiveDataRoom


    fun onLoad(movie: MovieDetailWrapperRoom) {

        viewModelScope.launch {

            try {

                Room.create().movieDao().insert(movie)
                Log.d(MovieDetailViewModel::class.java.name, "request OK - insert in DB")

            } catch (e: Exception) {
                Log.e(MovieDetailViewModel::class.java.name, "Error request - insert in DB")
                e.printStackTrace()
            }

        }

    }

    fun onDelete(movie: MovieDetailWrapperRoom) {

        viewModelScope.launch {

            try {

                Room.create().movieDao().deleteMovie(movie)
                Log.d(MovieDetailViewModel::class.java.name, "request OK - delete from DB")

            } catch (e: Exception) {
                Log.d(MovieDetailViewModel::class.java.name, "Error request - delete from DB")
                e.printStackTrace()
            }

        }



    }



    fun requestMovieDetail(id: Int) {

        viewModelScope.launch {

            try {

                // Movie Detail
                Log.d(MovieDetailViewModel::class.java.name,"Значение movieID before request - $id") // todo: вставить значение movieID

                mutableLiveDataMovieDetail.value = repositoryAPI.getMovieDetail(id.toString())

            }

            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Значение movieID before request with error - $id")
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request -  Movie Detail")
                e.printStackTrace()
            }


        }

    }


}