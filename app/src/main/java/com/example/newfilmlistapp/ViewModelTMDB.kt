package com.example.newfilmlistapp

import android.util.Log
import androidx.lifecycle.*
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ViewModelTMDB : ViewModel(), ViewModelProvider.Factory {


    private val retrofit: Retrofit by lazy { initRetrofit() }
    private val moshi: Moshi by lazy { initMoshi() }
    private val mutableLiveData_movie: MutableLiveData<MovieWrapper> = MutableLiveData()
    private val mutableLiveData_genres: MutableLiveData<GenresWrapper> = MutableLiveData()



    fun requestGenres() {

        viewModelScope.launch {

            try {

                mutableLiveData_genres.value = getGenres()!!
            }
            catch (e: Exception) {

                Log.d(ViewModelTMDB::class.java.name,"Error Request Genres")
                e.printStackTrace()
            }


        }


    }




    fun requestMovie(year: Int, genre: String, genresWrapper: GenresWrapper) {


        viewModelScope.launch {

            try {

                var id: Int = 0

                genresWrapper.genres.forEach {
                    if (it.name == genre)
                        id = it.id.toInt()
                }
                val string = id.toString()

                mutableLiveData_movie.value = getMovie(year, genre)!!
            }
            catch (e: Exception) {

                Log.d(ViewModelTMDB::class.java.name,"Error Request Movie")
                e.printStackTrace()
            }



        }
    }


    suspend fun getMovie(year: Int, genre: String): MovieWrapper {


        val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getMovie(primary_release_year = year, genres = genre)

        Log.d(ViewModelTMDB::class.java.name,"request OK")

        return result

    }


    suspend fun getGenres(): GenresWrapper {


        val loadingMovieDBService = retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getGenres()

         Log.d(ViewModelTMDB::class.java.name,"result: ${result.genres}")

        return result
    }

    fun getInstanceLiveDataMovie(): LiveData<MovieWrapper> { return mutableLiveData_movie }

    fun getInstanceLiveDataGenres(): LiveData<GenresWrapper> { return mutableLiveData_genres }


    fun initRetrofit(): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


        return retrofit


    }

    fun initMoshi(): Moshi {

        val moshi = Moshi.Builder()
            .build()

        return moshi

    }



}

// todo: Не работает запрос в сеть
// todo: Пытался в main сделать все равно нихера


