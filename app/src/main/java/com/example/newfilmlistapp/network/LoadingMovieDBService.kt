package com.example.newfilmlistapp.network

import com.example.newfilmlistapp.*
import com.example.newfilmlistapp.model.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
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


    companion object {


        fun create(): LoadingMovieDBService {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .build()

            return retrofit2.Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(LoadingMovieDBService::class.java)
        }
    }



}

