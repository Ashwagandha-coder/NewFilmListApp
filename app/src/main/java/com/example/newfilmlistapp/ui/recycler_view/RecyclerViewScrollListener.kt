package com.example.newfilmlistapp.ui.recycler_view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RecyclerViewScrollListener(private val scrollBack: ScrollBack) : RecyclerView.OnScrollListener() {

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


        visibleItemCount = recyclerView.childCount
        totalItemCount = recyclerView.layoutManager!!.itemCount

        firstVisibleItem = when (recyclerView.layoutManager) {
            is StaggeredGridLayoutManager -> (recyclerView.layoutManager as StaggeredGridLayoutManager)
                .findFirstCompletelyVisibleItemPositions(null)[0]
            else -> (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstVisibleItemPosition()
        }

        if (loading && totalItemCount > previousTotal) {
            loading = false
            previousTotal = totalItemCount
        }

        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            scrollBack.onScrollCompleted(firstVisibleItem, false)
            loading = true
        }

    }


}