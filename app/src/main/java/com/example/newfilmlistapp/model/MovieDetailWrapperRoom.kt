package com.example.newfilmlistapp.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
data class MovieDetailWrapperRoom (

    var adult: Boolean,

    var backdropPath: String? = null,

//    @Embedded
//    var belongsToCollection: Any? = null,

    var budget: Long?,
//    @Embedded
//    var genres: List<GenresRoom>? = null,
    var homepage: String? = null,
    @PrimaryKey(autoGenerate = false)
    var id: Long,

    var imdbID: String? = null,

    var originalLanguage: String,

    var originalTitle: String,

    var overview: String? = null,
    var popularity: Double,

    var posterPath: String? = null,

//    @Embedded
//    var productionCompanies: List<ProductionCompanyWrapperRoom>,

//    @Embedded
//    var productionCountries: List<ProductionCountryWrapperRoom>,

    var releaseDate: String? = null,

    var revenue: Long? = null,
    var runtime: Long? = null,

//    @Embedded
//    var spokenLanguages: List<SpokenLanguageWrapperRoom>,

    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean,

    var voteAverage: Double,

    var voteCount: Long,

    var isFavorite: Boolean? = false
)

data class GenresRoom(
     var id: Long,
     var name: String

)

data class ProductionCompanyWrapperRoom (
    var id: Long,

    @ColumnInfo(name = "logo_path")
    var logoPath: String? = null,

    var name: String? = null,

    @ColumnInfo(name = "origin_country")
    var originCountry: String? = null
)

data class ProductionCountryWrapperRoom (
    @ColumnInfo(name = "iso_3166_1")
    var iso3166_1: String? = null,

    var name: String? = null
)

data class SpokenLanguageWrapperRoom (
    @ColumnInfo(name = "iso_639_1")
    var iso639_1: String,
    var name: String
)
