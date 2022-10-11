package com.example.newfilmlistapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



@JsonClass(generateAdapter = true)
data class MovieDetailWrapper (

    val adult: Boolean,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any? = null,

    val budget: Long?,
    val genres: List<Genres>,
    val homepage: String? = null,
    val id: Long,

    @Json(name = "imdb_id")
    val imdbID: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    val overview: String? = null,
    val popularity: Double,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyWrapper>,

    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountryWrapper>,

    @Json(name = "release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long? = null,

    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageWrapper>,

    val status: String,
    val tagline: String? = null,
    val title: String,
    val video: Boolean,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Long,

)

@JsonClass(generateAdapter = true)
data class ProductionCompanyWrapper (
    val id: Long,

    @Json(name = "logo_path")
    val logoPath: String? = null,

    val name: String,

    @Json(name = "origin_country")
    val originCountry: String
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
    val iso639_1: String,

    val name: String
)
