package com.example.newfilmlistapp.repository.Impl

import com.example.newfilmlistapp.local.MovieDao
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom
import com.example.newfilmlistapp.repository.RepositoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ImplRepositoryRoom(private val movieDao: MovieDao): RepositoryRoom {


    override suspend fun insertDB() = withContext(Dispatchers.IO) {

    }

    override suspend fun updateDB() = withContext(Dispatchers.IO) {

    }

    override suspend fun getMovieListLocal(): List<MovieDetailWrapperRoom>? = withContext(Dispatchers.IO) {
        movieDao.getMovieList()
    }

    override suspend fun getMovieLocal(id: String): MovieDetailWrapperRoom? = withContext(Dispatchers.IO) {
        movieDao.getMovie(id)
    }

    override suspend fun insertLocal(movie: MovieDetailWrapperRoom) = withContext(Dispatchers.IO) {
        movieDao.insert(movie)
    }

    override suspend fun insertLocal(list: List<MovieDetailWrapperRoom>) = withContext(Dispatchers.IO) {
        movieDao.insert(list)
    }

    override suspend fun updateLocal(movie: MovieDetailWrapperRoom) = withContext(Dispatchers.IO) {
        movieDao.update(movie)
    }

    override suspend fun deleteMovieLocal(movie: MovieDetailWrapperRoom) = withContext(Dispatchers.IO) {
        movieDao.deleteMovie(movie)
    }

    override suspend fun deleteMovieLocal(id: String) = withContext(Dispatchers.IO) {
        movieDao.deleteMovie(id)
    }

    override suspend fun getMoviePageLocal(
        pageSize: Int,
        pageIndex: Int
    ): List<MovieDetailWrapperRoom>? = withContext(Dispatchers.IO) {
        movieDao.getMoviePage(pageSize, pageIndex)
    }

    override suspend fun getFavoriteLocal(
        pageSize: Int,
        pageIndex: Int
    ): List<MovieDetailWrapperRoom>? = withContext(Dispatchers.IO) {
        movieDao.getFavorite(pageSize, pageIndex)
    }
}