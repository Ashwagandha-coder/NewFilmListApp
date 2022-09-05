package com.example.newfilmlistapp.model

import android.graphics.Movie

data class MovieWrapper(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<Movie>?,
)


