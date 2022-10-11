package com.example.newfilmlistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "movie")
@JsonClass(generateAdapter = true)
data class MovieDetailWrapper (
    @PrimaryKey(autoGenerate = false)
    val adult: Boolean? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any? = null,

    val budget: Long? = null,
    val genres: List<Genres>? = null,
    val homepage: String? = null,
    val id: Long? = null,

    @Json(name = "imdb_id")
    val imdbID: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Double? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyWrapper>? = null,

    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountryWrapper>? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    val revenue: Long? = null,
    val runtime: Long? = null,

    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageWrapper>? = null,

    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "vote_count")
    val voteCount: Long? = null,

    var isFavorite: Boolean? = false
)

@JsonClass(generateAdapter = true)
data class ProductionCompanyWrapper (
    val id: Long? = null,

    @Json(name = "logo_path")
    val logoPath: String? = null,

    val name: String? = null,

    @Json(name = "origin_country")
    val originCountry: String? = null
)
@JsonClass(generateAdapter = true)
data class ProductionCountryWrapper (
    @Json(name = "iso_3166_1")
    val iso3166_1: String? = null,

    val name: String? = null
)
@JsonClass(generateAdapter = true)
data class SpokenLanguageWrapper (
    @Json(name = "iso_639_1")
    val iso639_1: String? = null,

    val name: String? = null
)
