package com.example.newfilmlistapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.view_model.ViewModel_Popular

class MoviePopularPagingSource(): PagingSource<Int, ResultPopular>() {


    override fun getRefreshKey(state: PagingState<Int, ResultPopular>): Int? {

        return null

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultPopular> {


        return try {

            val currentPage = params.key ?: 1
            val data = ViewModel_Popular().getPopularMovie().results ?: emptyList()

            LoadResult.Page(data = data,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}