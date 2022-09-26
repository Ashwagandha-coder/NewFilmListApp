package com.example.newfilmlistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newfilmlistapp.ViewModel.ViewModel_SortByDate
import com.example.newfilmlistapp.databinding.FragmentMovieDetailBinding


class MovieDetail : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: ViewModel_SortByDate by lazy {
        ViewModelProvider(this).get(ViewModel_SortByDate::class.java)
    }

    private val args: MovieDetailArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)

        onBackScreen()
        workWithViewModel()
        //

        // todo: Здесь был запрос requestMovieDetail

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun onBackScreen() {

        binding.topNavBar.returnImageView.setOnClickListener {

            val action = MovieDetailDirections.actionMovieDetailToRandom()
            it.findNavController().navigate(action)

        }

    }

    fun workWithViewModel() {

        viewModel.movieDetailWrapper.observe(viewLifecycleOwner,{

            val poster_path = it.posterPath

            binding.layoutMovie.visibility = View.VISIBLE
            binding.tvEmptyInfo.visibility = View.GONE

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${poster_path}")
                .into(binding.imgMovie)

            binding.tvMovieName.text = it.title
            binding.tvDescription.text = it.overview
            binding.tvRatingValue.text = it.voteAverage.toString()
            binding.tvYear.text = it.releaseDate.substring(0,3)



        })

    }




}