package com.example.newfilmlistapp.network

import com.example.newfilmlistapp.*
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieWrapper
import com.example.newfilmlistapp.model.Popular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.Year

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
        @Query("language") language: String = LANGUAGE
    ): GenresWrapper


    // Movie Detail

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(

    )





//    @GET("discover/movie")
//    suspend fun getMovie(
//        @Query("api_key") api_key: String = API_KEY,
//        @Query("language") language: String = LANGUAGE,
//        @Query("sort_by") sort_by: String = SORT_BY,
//        @Query("include_adult") include_adult: Boolean = INCLUDE_ADULT,
//        @Query("include_video") include_video: Boolean = INCLUDE_VIDEO,
//        @Query("page") page: Int = PAGE,
//        @Query("primary_release_year") primary_release_year: Int,
//        @Query("year") year: Int = primary_release_year,
//        @Query("with_genres") genres: String,
//        @Query("with_watch_monetization_types") with_watch_monetization_types: String = WITH_WATCH_MONETIZATION_TYPES): MovieWrapper
//

    // Random Movie

    @GET("discover/movie")
    suspend fun getMovie(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("primary_release_year") primary_release_year: Int,
        @Query("with_genres") genres: List<String>
    ): MovieWrapper


}