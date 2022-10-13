package com.example.newfilmlistapp.repository

import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.model.PopularWrapper

interface RepositoryAPI {

    suspend fun getPopularMovie(page: Int): PopularWrapper

    suspend fun getMovieDetail(movieID: String): MovieDetailWrapper

    suspend fun getMovie(year: Int, genre: String): MovieWrapper

    suspend fun getGenres(): GenresWrapper

    suspend fun getDefaultMovie(): MovieWrapper


}