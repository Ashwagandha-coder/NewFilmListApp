package com.example.newfilmlistapp.repository

import com.example.newfilmlistapp.model.PopularWrapper

interface RepositoryAPI {

    suspend fun getPopularMovie(page: Int): PopularWrapper


}