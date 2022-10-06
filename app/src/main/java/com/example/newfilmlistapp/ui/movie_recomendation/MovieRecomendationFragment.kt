package com.example.newfilmlistapp.ui.movie_recomendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newfilmlistapp.BASE_URL_FOR_PICTURE
import com.example.newfilmlistapp.databinding.FragmentMovieRecomendationBinding
import com.example.newfilmlistapp.view_model.MovieRecomendationViewModel
import com.example.newfilmlistapp.model.Genres


class MovieRecomendationFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentMovieRecomendationBinding
    private val viewModel: MovieRecomendationViewModel by viewModels()


    private var positionGenre = 0
    private var positionYear = 0

    // for navigation

    private var movie_ID: Int = 0
    private lateinit var poster_path: String
    private var vote_average: Float = 0.0F






    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieRecomendationBinding.inflate(inflater, container, false)

        bindGenres()
        bindYears()
        bindInfoMovie()
        setListenerButtonRequest()
        toMovieDetail()

        return binding.root
    }


    private fun bindInfoMovie() {

        viewModel.movie.observe(viewLifecycleOwner) {

            val random = (0..19).random()
            val movie = it.results[random]

            Glide.with(this)
                .load(BASE_URL_FOR_PICTURE + movie.posterPath)
                .apply(RequestOptions().centerCrop())
                .into(binding.posterPath)


            binding.overview.also {

                it.text = movie.overview

            }

            binding.releaseDate.also {

                it.text = movie.releaseDate

            }

            binding.title.also {

                it.text = movie.title
            }

            movie_ID = movie.id.toInt()
            poster_path = movie.posterPath
            vote_average = movie.voteAverage.toFloat()



        }


    }


    private fun bindGenres() {

        viewModel.genres.observe(viewLifecycleOwner)  {
                setupGenres(it.genres)
        }

    }

    private fun bindYears() {

        setupYears()
    }


    private fun setupGenres(genres: List<Genres>) {

        val listPopupWindowButton = binding.btnPopupMenuGenre
        val listPopupWindow = ListPopupWindow(this.requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)


        // Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton


        val arrayAdapterGenre = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            genres.map { it.name }
        )
        listPopupWindow.setAdapter(arrayAdapterGenre)

        // Set list popup's item click listener
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            positionGenre = position

            // Dismiss popup.
            listPopupWindow.dismiss()
        }

        // Show list popup window on button click.
        listPopupWindowButton.setOnClickListener { v: View? -> listPopupWindow.show() }


    }

    private fun setupYears() {

        val listPopupWindowButton = binding.btnPopupMenuYear
        val listPopupWindow = ListPopupWindow(this.requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)


        // Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton

        val listYear = mutableListOf<Int>()
        for (i in 2022..1874) {
            listYear.add(i)
        }
        val arrayAdapterYear = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listYear
        )

        listPopupWindow.setAdapter(arrayAdapterYear)

        // Set list popup's item click listener
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            positionYear = position

            // Dismiss popup.
            listPopupWindow.dismiss()
        }

        // Show list popup window on button click.
        listPopupWindowButton.setOnClickListener { v: View? -> listPopupWindow.show() }



    }


    private fun setListenerButtonRequest() {

        binding.request.setOnClickListener {

            viewModel.requestMovie(positionYear,positionGenre)


        }

    }


    private fun toMovieDetail() {

        binding.cardView.setOnClickListener {

            val action = MovieRecomendationFragmentDirections.actionRandomToMovieDetail(movie_ID,poster_path,vote_average)
            it.findNavController().navigate(action)

        }

    }




}





