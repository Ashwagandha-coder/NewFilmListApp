package com.example.newfilmlistapp.repository.Impl

import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.repository.RepositoryRoom


class ImplRepositoryRoom: RepositoryRoom {


    override suspend fun insertDB() {
        TODO("Not yet implemented")
    }

    override suspend fun updateDB() {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieListLocal(): List<MovieDetailWrapper>? {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieLocal(id: String): MovieDetailWrapper? {
        TODO("Not yet implemented")
    }

    override suspend fun insertLocal(movie: MovieDetailWrapper) {
        TODO("Not yet implemented")
    }

    override suspend fun insertLocal(list: List<MovieDetailWrapper>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocal(movie: MovieDetailWrapper) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovieLocal(movie: MovieDetailWrapper) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovieLocal(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getMoviePageLocal(
        pageSize: Int,
        pageIndex: Int
    ): List<MovieDetailWrapper>? {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteLocal(
        pageSize: Int,
        pageIndex: Int
    ): List<MovieDetailWrapper>? {
        TODO("Not yet implemented")
    }
}