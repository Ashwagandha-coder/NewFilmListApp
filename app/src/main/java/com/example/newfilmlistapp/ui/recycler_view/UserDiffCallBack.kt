package com.example.newfilmlistapp.ui.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.newfilmlistapp.model.ResultPopular

class UserDiffCallBack : DiffUtil.ItemCallback<ResultPopular>() {
    override fun areItemsTheSame(oldItem: ResultPopular, newItem: ResultPopular): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultPopular, newItem: ResultPopular): Boolean {
        return oldItem.id == newItem.id
    }
}