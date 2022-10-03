package com.example.newfilmlistapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newfilmlistapp.model.PopularWrapper

class MoviePopularPagingSource(): PagingSource<Int, PopularWrapper>() {


    override fun getRefreshKey(state: PagingState<Int, PopularWrapper>): Int? {

        return null

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularWrapper> {



    }
}