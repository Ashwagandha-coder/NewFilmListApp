package com.example.newfilmlistapp.network

import com.example.newfilmlistapp.API_KEY
import com.example.newfilmlistapp.LANGUAGE
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
    ): Response<MovieWrapper>


    // Genre


    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE): Response<Genres>



    // Years

    @GET("movie/{movie_id}/release_dates")
    suspend fun getRealeaseDate(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Path("movieID") movie_id: String?
    ): Response<Movie>

    // Movie_ID


    @GET("movie/{movie_id}")
    suspend fun getMovieId(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Path("movieID") movie_id: String?
    ): Response<Movie>







}