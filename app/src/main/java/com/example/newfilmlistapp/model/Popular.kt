package com.example.newfilmlistapp.model

import com.squareup.moshi.Json


data class PopularWrapper (
    val page: Long,
    val results: List<ResultPopular>,

    @Json(name = "total_results")
    val totalResults: Long,

    @Json(name = "total_pages")
    val totalPages: Long
)


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
    val originalLanguage: OriginalLanguage,

    val title: String,

    @Json(name = "backdrop_path")
    val backdropPath: String,

    val popularity: Double,

    @Json(name = "vote_count")
    val voteCount: Long,

    val video: Boolean,

    @Json(name = "vote_average")
    val voteAverage: Double
)

enum class OriginalLanguage(val value: String) {
    En("en");

    companion object {
        public fun fromValue(value: String): OriginalLanguage = when (value) {
            "en" -> En
            else -> throw IllegalArgumentException()
        }
    }
}

