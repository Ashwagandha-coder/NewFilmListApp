package com.example.newfilmlistapp.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.ui.UserDiffCallBack


class PopularAdapter() : ListAdapter<ResultPopular, PopularViewHolder>(UserDiffCallBack()) {

    // todo: Сделать логику скроллинга recycler view

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {

        val inflate_view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, null)
        return PopularViewHolder(inflate_view)

    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {

    }


}