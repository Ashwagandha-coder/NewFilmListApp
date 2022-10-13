package com.example.newfilmlistapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
data class MovieDetailWrapperRoom (

    @PrimaryKey(autoGenerate = false)
    val adult: Boolean,

    val backdropPath: String? = null,

//    val belongsToCollection: Any? = null,

    val budget: Long?,
    val genres: List<Genres>,
    val homepage: String? = null,
    val id: Long,

    val imdbID: String? = null,

    val originalLanguage: String,

    val originalTitle: String,

    val overview: String? = null,
    val popularity: Double,

    val posterPath: String? = null,

    val productionCompanies: List<ProductionCompanyWrapper>,

    val productionCountries: List<ProductionCountryWrapper>,

    val releaseDate: String,

    val revenue: Long,
    val runtime: Long? = null,

    val spokenLanguages: List<SpokenLanguageWrapper>,

    val status: String,
    val tagline: String? = null,
    val title: String,
    val video: Boolean,

    val voteAverage: Double,

    val voteCount: Long,

    var isFavorite: Boolean? = false
)
