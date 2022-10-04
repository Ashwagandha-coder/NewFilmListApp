package com.example.newfilmlistapp.ui.popular

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.newfilmlistapp.model.ResultPopular

class PopularPagedAdapter(diffCallBack: DiffUtil.ItemCallback<ResultPopular>) : PagingDataAdapter<ResultPopular, PopularViewHolder>(diffCallBack) {


    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        TODO("Not yet implemented")
    }
}