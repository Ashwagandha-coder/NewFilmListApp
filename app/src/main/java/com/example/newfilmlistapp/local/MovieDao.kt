package com.example.newfilmlistapp.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getMovieList(): List<MovieDetailWrapperRoom>?

    @Query("SELECT * FROM movie WHERE movie.id = :id")
    suspend fun getMovie(id: String): MovieDetailWrapperRoom?

    @Insert(onConflict = IGNORE)
    suspend fun insert(movie: MovieDetailWrapperRoom)

    @Insert(onConflict = IGNORE)
    suspend fun insert(list: List<MovieDetailWrapperRoom>)

    @Insert(onConflict = REPLACE)
    suspend fun update(movie: MovieDetailWrapperRoom)

    @Delete
    suspend fun deleteMovie(movie: MovieDetailWrapperRoom)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteMovie(id: String)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()

    @Query("SELECT * FROM movie LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getMoviePage(pageSize: Int, pageIndex: Int): List<MovieDetailWrapperRoom>?

    @Query("SELECT * FROM movie WHERE movie.isFavorite = 1 LIMIT :pageSize OFFSET ((:pageIndex-1)*:pageSize) ")
    suspend fun getFavorite(pageSize: Int, pageIndex: Int): List<MovieDetailWrapperRoom>?
}