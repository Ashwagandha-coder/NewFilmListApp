package com.example.newfilmlistapp.ui.recycler_view

import androidx.recyclerview.widget.RecyclerView

class RecyclerViewScrollListener() : RecyclerView.OnScrollListener() {

    private var loading: Boolean = true

    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var visibleThreshold: Int = 10
    private var previousTotal: Int = 0

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }


}