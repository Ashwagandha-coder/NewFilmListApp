package com.example.newfilmlistapp.repository.Impl

import android.util.Log
import com.example.newfilmlistapp.model.PopularWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.example.newfilmlistapp.network.Retrofit
import com.example.newfilmlistapp.repository.RepositoryAPI
import com.example.newfilmlistapp.view_model.PopularViewModel

class ImplRepositoryAPI: RepositoryAPI {


    override suspend fun getPopularMovie(page: Int): PopularWrapper {

        val loadingMovieDBService = Retrofit.retrofit.create(LoadingMovieDBService::class.java)

        val result = loadingMovieDBService.getPopularMovie(page = page)

        Log.d(PopularViewModel::class.java.name,"request OK - Popular Movie")

        return result

    }
}