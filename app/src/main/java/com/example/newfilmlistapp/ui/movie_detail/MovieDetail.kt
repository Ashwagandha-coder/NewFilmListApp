package com.example.newfilmlistapp.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newfilmlistapp.BASE_URL_FOR_PICTURE
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.view_model.ViewModel_MovieDetail
import com.example.newfilmlistapp.databinding.FragmentMovieDetailBinding


class MovieDetail : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: ViewModel_MovieDetail by lazy {
        ViewModelProvider(this).get(ViewModel_MovieDetail::class.java)
    }

    private val args: MovieDetailArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)

        onBackScreen()
        workWithViewModel()
        requestWrapper()

        return binding.root
    }

    private fun requestWrapper() {

        val id: Int = args.movieID
        viewModel.requestMovieDetail(id)

    }

    private fun onBackScreen() {

        binding.topNavBar.returnImageView.setOnClickListener {

            val action = MovieDetailDirections.actionMovieDetailToRandom()
            it.findNavController().navigate(action)

        }

    }

    private fun workWithViewModel() {

        viewModel.movieDetailWrapper.observe(viewLifecycleOwner) {

            val backdrop_path = it.backdropPath

            val poster_path = args.posterPath

            val vote_average = args.voteAverage

            val runtime = it.runtime

            val runtimeStr = "${runtime.toInt() / 60}h ${runtime.toInt() % 60}m"
            if (runtime.toInt() >= 60) {
                binding.movieRuntime.apply {
                    text = runtimeStr
                }
            } else {
                binding.movieRuntime.apply {
                    text = runtime.toString()
                }
            }


            binding.progressBar.apply {
                progress = (vote_average * 10).toInt()
            }

            val progressStr: String = binding.progressBar.progress.toString() + "%"

            binding.progressText.apply {
                text = progressStr
            }

            binding.movieStatus.setImageResource(R.drawable.ic_realise)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${backdrop_path}")
                .apply(RequestOptions().centerCrop())
                .into(binding.imgMovie)

            Glide.with(this)
                .load(BASE_URL_FOR_PICTURE + poster_path)
                .apply(RequestOptions().centerCrop())
                .into(binding.poster)

            binding.tvMovieName.text = it.title
            binding.tvDescription.text = it.overview
           // binding.tvRatingValue.text = it.voteAverage.toString()
            binding.tvYear.text = it.releaseDate.substring(0,3)


        }

    }




}