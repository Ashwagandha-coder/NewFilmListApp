package com.example.newfilmlistapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.example.newfilmlistapp.network.Retrofit
import kotlinx.coroutines.launch

class ViewModel_MovieDetail: ViewModel() {

    private val mutableLiveData_movie_detail: MutableLiveData<MovieDetailWrapper> = MutableLiveData()
    val movieDetailWrapper: LiveData<MovieDetailWrapper> = mutableLiveData_movie_detail





    fun requestMovieDetail() {

        viewModelScope.launch {

            try {


//                genresWrapper = mutableLiveData_genres.value!!
//
//                val variable = getMovie(year, genresWrapper.genres.get(index).toString())!!
//
//
//                number_random = Random.Default.nextInt(0,7)
//
//                Log.d(ViewModelTMDB::class.java.name,"Random number in REQUEST_MOVIE_DETAIL - $number_random")
//
//                movie_ID = variable.results.get(number_random)?.id.toString() ?: ""
//
//                Log.d(ViewModelTMDB::class.java.name,movie_ID + " " + "Значение movie_ID in REQUEST_MOVIE_DETAIL")



                // Movie Detail
                //   Log.d(ViewModelTMDB::class.java.name,"Значение movieID before request - $movie_ID")

              //  mutableLiveData_movie_detail.value = getMovieDetail("550")!!


            }

            catch (e: Exception) {
               // Log.d(ViewModel_SortByDate::class.java.name,"Значение movieID before request with error - $movie_ID")
                Log.d(ViewModel_SortByDate::class.java.name,"Error Request -  Movie Detail")
                e.printStackTrace()
            }


        }

    }

    suspend fun getMovieDetail(movieID: String): MovieDetailWrapper {

        val loadingMovieDBService = Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getMovieDetail(movieID)

        Log.d(ViewModel_SortByDate::class.java.name, "request OK - Movie Detail ")


        return result

    }

}