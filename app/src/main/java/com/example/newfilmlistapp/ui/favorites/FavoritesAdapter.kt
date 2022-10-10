package com.example.newfilmlistapp.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newfilmlistapp.BASE_URL_FOR_PICTURE
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.ui.popular.PopularViewHolder
import com.example.newfilmlistapp.ui.recycler_view.UserDiffCallBackFavorite

class FavoritesAdapter(): PagingDataAdapter<MovieDetailWrapper,PopularViewHolder>(UserDiffCallBackFavorite()) {

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {

        val inflate_view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, null)
        return PopularViewHolder(inflate_view)

    }

    override fun onBindViewHolder(viewHolder: PopularViewHolder, position: Int) {

        val movie: MovieDetailWrapper = getItem(position)!!
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

        toMovieDetail(viewHolder, movie.id.toInt(),movie.posterPath, movie.voteAverage.toFloat())

    }

    private fun toMovieDetail(viewHolder: PopularViewHolder, movieID: Int, poster_path: String?, vote_average: Float) {

        viewHolder.cardView.setOnClickListener {

            val action = FavoritesFragmentDirections.actionFavoriteToMovieDetailFavorite(movieID, poster_path!!,vote_average)
            it.findNavController().navigate(action)

        }

    }




}