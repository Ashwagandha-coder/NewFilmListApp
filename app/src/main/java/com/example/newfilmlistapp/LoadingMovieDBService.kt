package com.example.newfilmlistapp

import retrofit2.http.GET

interface LoadingMovieDBService {



    @GET("movie/popular")
    suspend fun getPopularMovie(api_key: String, language: String, page: Int)




}