package com.example.newfilmlistapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newfilmlistapp.model.MovieDetailWrapper

class MovieFavoritePagingSource: PagingSource<Int,MovieDetailWrapper>() {


    override fun getRefreshKey(state: PagingState<Int, MovieDetailWrapper>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailWrapper> {
        TODO("Not yet implemented")
    }
}