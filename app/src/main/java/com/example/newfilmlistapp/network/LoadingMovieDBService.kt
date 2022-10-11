package com.example.newfilmlistapp.network

import com.example.newfilmlistapp.*
import com.example.newfilmlistapp.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface LoadingMovieDBService {


    // Popular

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 7
    ): PopularWrapper


    // Genre


    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): GenresWrapper


    // Movie Detail

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): MovieDetailWrapper



    // Random Movie

    @GET("discover/movie")
    suspend fun getMovie(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("primary_release_year") primary_release_year: Int,
        @Query("with_genres") genres: List<String>
    ): MovieWrapper


    // Default Movie

    @GET("discover/movie")
    suspend fun getDefaultMovie(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ) : MovieWrapper


}