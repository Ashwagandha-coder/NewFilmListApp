package com.example.newfilmlistapp.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
data class MovieDetailWrapperRoom(

    var adult: Boolean,

    var backdropPath: String? = null,

    var budget: Long?,

    var homepage: String? = null,

    @PrimaryKey(autoGenerate = false)
    var id: Long,

    var imdbID: String? = null,

    var originalLanguage: String,

    var originalTitle: String,

    var overview: String? = null,
    var popularity: Double,

    var posterPath: String? = null,


    var releaseDate: String? = null,

    var revenue: Long? = null,
    var runtime: Long? = null,


    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean,

    var voteAverage: Double,

    var voteCount: Long,

    var isFavorite: Boolean? = false
)

