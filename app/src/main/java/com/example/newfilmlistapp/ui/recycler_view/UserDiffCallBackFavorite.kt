package com.example.newfilmlistapp.ui.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.newfilmlistapp.model.MovieDetailWrapper

class UserDiffCallBackFavorite : DiffUtil.ItemCallback<MovieDetailWrapper>() {
    override fun areItemsTheSame(oldItem: MovieDetailWrapper, newItem: MovieDetailWrapper): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetailWrapper, newItem: MovieDetailWrapper): Boolean {
        return oldItem.id == newItem.id
    }
}