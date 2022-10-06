package com.example.newfilmlistapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newfilmlistapp.model.PopularWrapper
import com.example.newfilmlistapp.repository.RepositoryForRoom

class FavoritesViewModel(private val repositoryForRoom: RepositoryForRoom) : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<PopularWrapper> = MutableLiveData()
    val favorite = mutableLiveData_favorite





//
//    private fun onLoad(page: Int, items: List<ResultPopular>) {
//
//        // load success then update current page
//        currentPage.value = page
//        // case load first page then clear data from listItem
//        if (currentPage.value == getFirstPage()) itemList.value?.clear()
//        // case refresh then reset load more
//        if (isRefreshing.value == true) resetLoadMore()
//
//        // add new data to listItem
//        val newList = itemList.value ?: ArrayList()
//        newList.addAll(items ?: listOf())
//        itemList.value = newList
//
//        // check last page
//        isLastPage.value = items?.size ?: 0 < getNumberItemPerPage()
//
//        // reset load
//        hideLoading()
//        isRefreshing.value = false
//        isLoadMore.value = false
//
//        // check empty list
//        checkEmptyList()
//
//
//    }


}