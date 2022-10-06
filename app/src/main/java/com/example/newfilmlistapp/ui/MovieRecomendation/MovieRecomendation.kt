package com.example.newfilmlistapp.ui.MovieRecomendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.newfilmlistapp.BASE_URL_FOR_PICTURE
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.databinding.FragmentMovieRecomendationBinding
import com.example.newfilmlistapp.view_model.ViewModel_SortByDate
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.model.MovieWrapper


class MovieRecomendation : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentMovieRecomendationBinding
    private val viewModel: ViewModel_SortByDate by viewModels()
    private lateinit var movieWrapper: MovieWrapper

    private var positionGenre = 0
    private var positionYear = 0

    // for navigation



//    private var movie_ID: Int = 0
//    private lateinit var poster_path: String
//    private var vote_average: Float = 0.0F




    // Save Date

//    private var year: Int = 0
//    private var genre: Int = 0
//    private var poster_path: String = ""
//    private var tv_below_poster: String = ""



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieRecomendationBinding.inflate(inflater, container, false)

        bindGenres()
        setupYears()
        setListenerButton()
        toMovieDetail()

        return binding.root
    }


    override fun onPause() {
        super.onPause()
    }




    private fun bindGenres() {

        viewModel.genres.observe(viewLifecycleOwner)  {
                setupGenres(it.genres)
        }


    }


    private fun bindYears() {

        viewModel.movie.observe(viewLifecycleOwner) {

        }

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

        val listYear = mutableListOf<Int>()
        for (i in 2022..1874) {
            listYear.add(i)
        }
        val arrayAdapterYear = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listYear
        )



    }


    fun setListenerButton() {

        binding.request.setOnClickListener {

            val year = getItemSpinnerYear()
            val genre = getItemSpinnerGenre()

            viewModel.requestMovie(year, genre)



        }

    }


    fun getItemSpinnerGenre(): Int {

        var result: Int = 0

        binding.genre.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                result = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Stub
            }
        }

        return result

    }


    fun getItemSpinnerYear(): Int {

        var result: Int = 0

        binding.years.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                result = p0!!.adapter.getItem(p2) as Int
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Stub
            }

        }

        return result


    }


    fun toMovieDetail() {

        binding.pictureFilm.setOnClickListener {

            val action = MovieRecomendationDirections.actionRandomToMovieDetail(movie_ID,poster_path,vote_average)
            it.findNavController().navigate(action)

        }

    }


}





