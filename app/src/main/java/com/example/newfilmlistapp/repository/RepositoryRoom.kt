package com.example.newfilmlistapp.repository

import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.model.ResultPopular


interface RepositoryRoom {

    suspend fun insertDB()

    suspend fun updateDB()

    suspend fun getMovieListLocal(): List<MovieDetailWrapper>?

    suspend fun getMovieLocal(id: String): MovieDetailWrapper?

    suspend fun insertLocal(movie: MovieDetailWrapper)

    suspend fun insertLocal(list: List<MovieDetailWrapper>)

    suspend fun updateLocal(movie: MovieDetailWrapper)

    suspend fun deleteMovieLocal(movie: MovieDetailWrapper)

    suspend fun deleteMovieLocal(id: String)

    suspend fun getMoviePageLocal(pageSize: Int, pageIndex: Int): List<MovieDetailWrapper>?

    suspend fun getFavoriteLocal(pageSize: Int, pageIndex: Int): List<MovieDetailWrapper>?


}