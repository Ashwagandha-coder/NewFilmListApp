package com.example.newfilmlistapp.repository.Impl

import android.util.Log
import com.example.newfilmlistapp.model.PopularWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.example.newfilmlistapp.repository.RepositoryAPI
import com.example.newfilmlistapp.view_model.popular.PopularViewModel

class ImplRepositoryAPI: RepositoryAPI {

    override suspend fun getPopularMovie(page: Int): PopularWrapper {

        val service = LoadingMovieDBService.create()

        val result = service.getPopularMovie(page = page)

        Log.d(PopularViewModel::class.java.name,"request OK - Popular Movie")

        return result

    }
}