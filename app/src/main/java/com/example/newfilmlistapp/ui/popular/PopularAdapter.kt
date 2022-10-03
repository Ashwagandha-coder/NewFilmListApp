package com.example.newfilmlistapp.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newfilmlistapp.BASE_URL_FOR_PICTURE
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.ui.UserDiffCallBack


class PopularAdapter() : ListAdapter<ResultPopular, PopularViewHolder>(UserDiffCallBack()) {


    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {

        val inflate_view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, null)
        return PopularViewHolder(inflate_view)

    }

    override fun onBindViewHolder(viewHolder: PopularViewHolder, position: Int) {

        val movie: ResultPopular = getItem(position)
        val date = movie.releaseDate


        viewHolder.movieName.also {
            it.text = movie.title
        }

        viewHolder.movieOverview.also {
            it.text = movie.overview
        }

        viewHolder.movieReleaseDate.also {
            it.text = date
        }

        if (movie.posterPath != null) {
            Glide.with(viewHolder.posterPath.context)
                .load(BASE_URL_FOR_PICTURE + movie.posterPath)
                .apply(RequestOptions().centerCrop())
                .into(viewHolder.posterPath)
        }

    }


}