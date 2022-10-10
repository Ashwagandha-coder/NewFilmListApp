package com.example.newfilmlistapp.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.newfilmlistapp.model.MovieDetailWrapper


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getMovieList(): List<MovieDetailWrapper>?

    @Query("SELECT * FROM movie WHERE movie.id = :id")
    suspend fun getMovie(id: String): MovieDetailWrapper?

    @Insert(onConflict = IGNORE)
    suspend fun insert(movie: MovieDetailWrapper)

    @Insert(onConflict = IGNORE)
    suspend fun insert(list: List<MovieDetailWrapper>)

    @Insert(onConflict = REPLACE)
    suspend fun update(movie: MovieDetailWrapper)

    @Delete
    suspend fun deleteMovie(movie: MovieDetailWrapper)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteMovie(id: String)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()

    @Query("SELECT * FROM movie LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getMoviePage(pageSize: Int, pageIndex: Int): List<MovieDetailWrapper>?

    @Query("SELECT * FROM movie WHERE movie.isFavorite = 1 LIMIT :pageSize OFFSET ((:pageIndex-1)*:pageSize) ")
    suspend fun getFavorite(pageSize: Int, pageIndex: Int): List<MovieDetailWrapper>?
}