package com.example.newfilmlistapp.repository.Impl

import android.util.Log
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.model.PopularWrapper
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.example.newfilmlistapp.repository.RepositoryAPI
import com.example.newfilmlistapp.view_model.movie_recomendation.MovieRecomendationViewModel
import com.example.newfilmlistapp.view_model.popular.PopularViewModel

class ImplRepositoryAPI: RepositoryAPI {

    private val service = RepositoryAPI.create()

    override suspend fun getPopularMovie(page: Int): PopularWrapper {

        val result = service.getPopularMovie(page = page)

        Log.d(PopularViewModel::class.java.name,"request OK - Popular Movie")

        return result

    }


    override suspend fun getMovieDetail(movieID: String): MovieDetailWrapper {

        val result = service.getMovieDetail(movieID)

        Log.d(MovieRecomendationViewModel::class.java.name, "request OK - Movie Detail ")

        return result

    }

    override suspend fun getMovie(year: Int?, genre: String?): MovieWrapper {

        val result = service.getMovie(primary_release_year = year, genres = listOf(genre))

        Log.d(MovieRecomendationViewModel::class.java.name,"request OK in suspend - Random Movie ")

        return result

    }


    override suspend fun getGenres(): GenresWrapper {

        val result = service.getGenres()

        Log.d(MovieRecomendationViewModel::class.java.name,"request OK in suspend - Genres")

        return result
    }


    override suspend fun getDefaultMovie(): MovieWrapper {

        val result = service.getDefaultMovie()

        Log.d(MovieRecomendationViewModel::class.java.name,"request OK in suspend - Default Movie ")

        return result


    }



}