package com.example.newfilmlistapp.ui.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom

class UserDiffCallBackFavorite : DiffUtil.ItemCallback<MovieDetailWrapperRoom>() {
    override fun areItemsTheSame(oldItem: MovieDetailWrapperRoom, newItem: MovieDetailWrapperRoom): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetailWrapperRoom, newItem: MovieDetailWrapperRoom): Boolean {
        return oldItem.id == newItem.id
    }
}