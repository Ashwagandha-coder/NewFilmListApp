package com.example.newfilmlistapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.repository.RepositoryAPI

class MoviePopularPagingSource(private val repositoryAPI: RepositoryAPI): PagingSource<Int, ResultPopular>() {

    // todo: Проблема с запросом при добавлении Query page
    // todo: Не проходит запрос из за page параметра


    override fun getRefreshKey(state: PagingState<Int, ResultPopular>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultPopular> {


        return try {

            val currentPage = params.key ?: 1
            val data = repositoryAPI.getPopularMovie(currentPage).results

            LoadResult.Page(data = data,
                prevKey = null,
                nextKey = currentPage.plus(1)
            )
        }
        catch (e: Exception) {
            Log.e("MoviePopularPaging", "error $e")
            LoadResult.Error(e)
        }

    }
}