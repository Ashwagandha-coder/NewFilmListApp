package com.example.newfilmlistapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.repository.RepositoryAPI
import com.example.newfilmlistapp.view_model.ViewModel_Popular

class MoviePopularPagingSource(private val repositoryAPI: RepositoryAPI): PagingSource<Int, ResultPopular>() {


    override fun getRefreshKey(state: PagingState<Int, ResultPopular>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultPopular> {


        return try {

            val currentPage = params.key ?: 1
            val data = repositoryAPI.getPopularMovie().results ?: emptyList()

            LoadResult.Page(data = data,
                prevKey = null,
                nextKey = currentPage.plus(1)
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}