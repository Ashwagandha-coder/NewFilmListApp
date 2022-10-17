package com.example.newfilmlistapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GenresWrapper(
    @Json(name = "genres") val genres: List<Genres>,
)

@JsonClass(generateAdapter = true)
data class Genres(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String
)



