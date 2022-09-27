package com.example.newfilmlistapp.model

data class SaveDataBackScreen(
    val year:Int,
    val index_genre: Int,
    val poster_path: String,
    val movieWrapper: MovieWrapper
)