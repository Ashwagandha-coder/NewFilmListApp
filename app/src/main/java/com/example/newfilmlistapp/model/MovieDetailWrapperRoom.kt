package com.example.newfilmlistapp.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
data class MovieDetailWrapperRoom (

    @PrimaryKey(autoGenerate = false)
    val adult: Boolean,

    val backdropPath: String? = null,

    val belongsToCollection: Any? = null,

    val budget: Long?,
    val genres: List<GenresRoom>? = null,
    val homepage: String? = null,
    val id: Long,

    val imdbID: String? = null,

    val originalLanguage: String,

    val originalTitle: String,

    val overview: String? = null,
    val popularity: Double,

    val posterPath: String? = null,

    val productionCompanies: List<ProductionCompanyWrapperRoom>,

    val productionCountries: List<ProductionCountryWrapperRoom>,

    val releaseDate: String? = null,

    val revenue: Long? = null,
    val runtime: Long? = null,

    val spokenLanguages: List<SpokenLanguageWrapperRoom>,

    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean,

    val voteAverage: Double,

    val voteCount: Long,

    var isFavorite: Boolean? = false
)

data class GenresRoom(
     val id: Long,
     val name: String
)

data class ProductionCompanyWrapperRoom(
    val id: Long,

    @ColumnInfo(name = "logo_path")
    val logoPath: String? = null,

    val name: String? = null,

    @ColumnInfo(name = "origin_country")
    val originCountry: String? = null
)

data class ProductionCountryWrapperRoom(
    @ColumnInfo(name = "iso_3166_1")
    val iso3166_1: String? = null,

    val name: String? = null
)

data class SpokenLanguageWrapperRoom(
    @ColumnInfo(name = "iso_639_1")
    val iso639_1: String,

    val name: String
)
