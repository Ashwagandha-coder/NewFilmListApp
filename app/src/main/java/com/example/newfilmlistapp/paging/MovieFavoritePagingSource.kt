package com.example.newfilmlistapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom
import com.example.newfilmlistapp.repository.RepositoryRoom

class MovieFavoritePagingSource(private val repositoryRoom: RepositoryRoom): PagingSource<Int,MovieDetailWrapperRoom>() {


    override fun getRefreshKey(state: PagingState<Int, MovieDetailWrapperRoom>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailWrapperRoom> {

        return try {

            val currentPage = params.key ?: 1
            val data = repositoryRoom.getMovieListLocal()

            LoadResult.Page(data = data ?: emptyList(),
                prevKey = null,
                nextKey = currentPage.plus(1)
            )
            LoadResult.Error(Exception())
        }
        catch (e: Exception) {
            Log.e("MovieFavoritePaging", "error $e")
            LoadResult.Error(e)
        }

    }
}