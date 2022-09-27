package com.example.newfilmlistapp.ui.popular

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newfilmlistapp.model.ResultPopular


class PopularAdapter() : ListAdapter<ResultPopular, PopularViewHolder>() {

    // todo: Сделать логику скроллинга recycler view

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {


    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {

    }


}