package com.example.newfilmlistapp.network

import com.example.newfilmlistapp.API_KEY
import com.example.newfilmlistapp.LANGUAGE
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.Popular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoadingMovieDBService {


    // Popular

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Popular


    // Genre


    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE): GenresWrapper











}