package com.example.newfilmlistapp.repository

import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.model.ResultPopular

// todo: Продумать какие данные нужны для CardView

interface RepositoryRoom {

    suspend fun insertDB()

    suspend fun updateDB()

    suspend fun getMovieListLocal(): List<MovieDetailWrapper>?

    suspend fun getMovieLocal(id: String): ResultPopular?

    suspend fun insertLocal(movie: ResultPopular)

    suspend fun insertLocal(list: List<ResultPopular>)

    suspend fun updateLocal(movie: ResultPopular)

    suspend fun deleteMovieLocal(movie: ResultPopular)

    suspend fun deleteMovieLocal(id: String)

    suspend fun getMoviePageLocal(pageSize: Int, pageIndex: Int): List<ResultPopular>?

    suspend fun getFavoriteLocal(pageSize: Int, pageIndex: Int): List<ResultPopular>?


}