package com.example.newfilmlistapp.repository.Impl


import com.example.newfilmlistapp.local.db.AppDatabase
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom
import com.example.newfilmlistapp.repository.RepositoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ImplRepositoryRoom(private val room: AppDatabase): RepositoryRoom {


    override suspend fun insertDB() = withContext(Dispatchers.IO) {

    }

    override suspend fun updateDB() = withContext(Dispatchers.IO) {

    }

    override suspend fun getMovieListLocal(): List<MovieDetailWrapperRoom>? = withContext(Dispatchers.IO) {
        room.movieDao().getMovieList()
    }

    override suspend fun getMovieLocal(id: String): MovieDetailWrapperRoom? = withContext(Dispatchers.IO) {
        room.movieDao().getMovie(id)
    }

    override suspend fun insertLocal(movie: MovieDetailWrapperRoom) = withContext(Dispatchers.IO) {
        room.movieDao().insert(movie)
    }

    override suspend fun insertLocal(list: List<MovieDetailWrapperRoom>) = withContext(Dispatchers.IO) {
        room.movieDao().insert(list)
    }

    override suspend fun updateLocal(movie: MovieDetailWrapperRoom) = withContext(Dispatchers.IO) {
        room.movieDao().update(movie)
    }

    override suspend fun deleteMovieLocal(movie: MovieDetailWrapperRoom) = withContext(Dispatchers.IO) {
        room.movieDao().deleteMovie(movie)
    }

    override suspend fun deleteMovieLocal(id: String) = withContext(Dispatchers.IO) {
        room.movieDao().deleteMovie(id)
    }

    override suspend fun getMoviePageLocal(
        pageSize: Int,
        pageIndex: Int
    ): List<MovieDetailWrapperRoom>? = withContext(Dispatchers.IO) {
        room.movieDao().getMoviePage(pageSize, pageIndex)
    }

    override suspend fun getFavoriteLocal(
        pageSize: Int,
        pageIndex: Int
    ): List<MovieDetailWrapperRoom>? = withContext(Dispatchers.IO) {
        room.movieDao().getFavorite(pageSize, pageIndex)
    }
}