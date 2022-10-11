package com.example.newfilmlistapp.repository

import com.example.newfilmlistapp.model.MovieDetailWrapperRoom


interface RepositoryRoom {

    suspend fun insertDB()

    suspend fun updateDB()

    suspend fun getMovieListLocal(): List<MovieDetailWrapperRoom>?

    suspend fun getMovieLocal(id: String): MovieDetailWrapperRoom?

    suspend fun insertLocal(movie: MovieDetailWrapperRoom)

    suspend fun insertLocal(list: List<MovieDetailWrapperRoom>)

    suspend fun updateLocal(movie: MovieDetailWrapperRoom)

    suspend fun deleteMovieLocal(movie: MovieDetailWrapperRoom)

    suspend fun deleteMovieLocal(id: String)

    suspend fun getMoviePageLocal(pageSize: Int, pageIndex: Int): List<MovieDetailWrapperRoom>?

    suspend fun getFavoriteLocal(pageSize: Int, pageIndex: Int): List<MovieDetailWrapperRoom>?


}