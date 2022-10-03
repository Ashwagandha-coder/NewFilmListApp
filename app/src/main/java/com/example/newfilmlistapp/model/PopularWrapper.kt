package com.example.newfilmlistapp.model

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularWrapper (
    val page: Long,
    val results: List<ResultPopular>,

    @Json(name = "total_results")
    val totalResults: Long,

    @Json(name = "total_pages")
    val totalPages: Long
)

@JsonClass(generateAdapter = true)
data class ResultPopular (
    @Json(name = "poster_path")
    val posterPath: String,

    val adult: Boolean,
    val overview: String,

    @Json(name = "release_date")
    val releaseDate: String,

    @Json(name = "genre_ids")
    val genreIDS: List<Long>,

    val id: Long,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "original_language")
    val originalLanguage: String,

    val title: String,

    @Json(name = "backdrop_path")
    val backdropPath: String,

    val popularity: Double,

    @Json(name = "vote_count")
    val voteCount: Long,

    val video: Boolean,

    @Json(name = "vote_average")
    val voteAverage: Double,

)
@JsonClass(generateAdapter = true)
data class OriginalLanguage(val value: String)


