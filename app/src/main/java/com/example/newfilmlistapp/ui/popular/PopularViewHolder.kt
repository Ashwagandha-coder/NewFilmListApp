package com.example.newfilmlistapp.ui.popular

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.databinding.FragmentPopularBinding

class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    val cardView: CardView = itemView.findViewById(R.id.cardView)
    val movieName: TextView = itemView.findViewById(R.id.title)
    val posterPath: ImageView = itemView.findViewById(R.id.poster_path)
    val movieReleaseDate: TextView = itemView.findViewById(R.id.release_date)
    val movieOverview: TextView = itemView.findViewById(R.id.overview)


}