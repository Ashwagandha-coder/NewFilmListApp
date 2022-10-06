package com.example.newfilmlistapp.ui.MovieRecomendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.newfilmlistapp.BASE_URL_FOR_PICTURE
import com.example.newfilmlistapp.databinding.FragmentMovieRecomendationBinding
import com.example.newfilmlistapp.view_model.ViewModel_SortByDate
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.model.MovieWrapper


class MovieRecomendation : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentMovieRecomendationBinding
    private val viewModel: ViewModel_SortByDate by viewModels()
    private lateinit var movieWrapper: MovieWrapper

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

            movieWrapper = it

            val index = viewModel.array_index

            binding.textBelowPictureFilm.text = it?.results?.get(index)?.originalTitle ?: "75 string SortByDate"
            val tv_below_poster = it?.results?.get(index)?.originalTitle ?: "76 string SortByDate"

            val poster_path_local = it?.results?.get(index)?.posterPath ?: "77 string SortByDate"

            val vote_average_local = it?.results?.get(index)?.voteAverage


            Glide.with(this)
                .load(BASE_URL_FOR_PICTURE + "${poster_path_local}")
                .into(binding.pictureFilm)

            // movieID

            movie_ID = it.results.get(index).id.toInt()

            // Poster Path

            poster_path = poster_path_local

            // Vote Average

            vote_average = vote_average_local?.toFloat()!!



        }

    }



    private fun setupGenres(genres: List<Genres>) {
        val arrayAdapterGenre = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            genres.map { it.name }
        )
        binding.genre.adapter = arrayAdapterGenre

    }

    private fun setupYears() {

        viewModel.requestGenres()
        val listYear = mutableListOf<Int>()
        for (i in 2022..1874) {
            listYear.add(i)
        }

        val arrayAdapterYear = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listYear
        )

        binding.years.adapter = arrayAdapterYear

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





