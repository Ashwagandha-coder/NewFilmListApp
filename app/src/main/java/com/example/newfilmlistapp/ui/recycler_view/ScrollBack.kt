package com.example.newfilmlistapp.ui.recycler_view

interface ScrollBack {
    fun onScrollCompleted(firstVisibleItem: Int, isLoadingMoreData: Boolean)
}