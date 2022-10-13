package com.example.newfilmlistapp.ui.movie_recomendation

import android.os.Bundle
import android.util.Log
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
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.databinding.FragmentMovieRecomendationBinding
import com.example.newfilmlistapp.view_model.movie_recomendation.MovieRecomendationViewModel
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.repository.Impl.ImplRepositoryAPI
import com.example.newfilmlistapp.view_model.movie_recomendation.MovieRecomendationViewModelFactory


class MovieRecomendationFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentMovieRecomendationBinding
    private val viewModel: MovieRecomendationViewModel by viewModels { MovieRecomendationViewModelFactory(ImplRepositoryAPI()) }


    private lateinit var genre: String
    private var year = 0

    // for navigation

    private var movieID: Int = 0
    private lateinit var posterPath: String
    private var voteAverage: Float = 0.0F


    private var listDefaultMovie: MutableList<com.example.newfilmlistapp.model.Result>? = null
    private var listMovie: MutableList<com.example.newfilmlistapp.model.Result>? = null


    private var movieRecomendationView: View? = null
    val viewMovie = movieRecomendationView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieRecomendationBinding.inflate(inflater, container, false)

        bindGenres()
        bindYears()
        //setupDefaultMovie()
        setupMovie()
        setListenerButtonRequest()
        toMovieDetail()

        movieRecomendationView = binding.root

        return binding.root
    }



    private fun setupMovie() {

        viewModel.movie.observe(viewLifecycleOwner) {

            if (listMovie == null) {

                val movie = it

                listMovie = mutableListOf(movie)
                listDefaultMovie!!.add(movie)

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

                movieID = movie.id.toInt()
                posterPath = movie.posterPath
                voteAverage = movie.voteAverage.toFloat()

            }

            else {

                    val movie = listMovie!![listMovie!!.lastIndex]
                    listDefaultMovie!!.add(movie)


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

                    movieID = movie.id.toInt()
                    posterPath = movie.posterPath
                    voteAverage = movie.voteAverage.toFloat()

                listMovie = null

            }
        }


        }



    private fun bindGenres() {
        viewModel.requestGenres()
        viewModel.genres.observe(viewLifecycleOwner) {
            setupGenres(it.genres)
        }

    }

    private fun bindYears() {
        setupYears()
    }


    private fun setupGenres(genres: List<Genres>) {

        val listPopupWindowButton = binding.btnPopupMenuGenre
        val listPopupWindow = ListPopupWindow(
            this.requireContext(),
            null,
            androidx.appcompat.R.attr.listPopupWindowStyle
        )


        // Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton


        val arrayAdapterGenre = ArrayAdapter(
            requireContext(),
            R.layout.item_popup_menu,
            genres.map { it.name }
        )
        listPopupWindow.setAdapter(arrayAdapterGenre)

        // Set list popup's item click listener
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            this.genre = genres[position].id.toString()

            Log.d(MovieRecomendationFragment::class.java.name, "position genre: " + position)

            Log.d(
                MovieRecomendationFragment::class.java.name,
                "genre  " + genres[position].id.toString()
            )

            listPopupWindowButton.text = genres[position].name

            // Dismiss popup.
            listPopupWindow.dismiss()
        }

        // Show list popup window on button click.
        listPopupWindowButton.setOnClickListener { v: View? -> listPopupWindow.show() }


    }

    private fun setupYears() {

        val listPopupWindowButton = binding.btnPopupMenuYear
        val listPopupWindow = ListPopupWindow(
            this.requireContext(),
            null,
            androidx.appcompat.R.attr.listPopupWindowStyle
        )


        // Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton

        val listYear = mutableListOf<Int>()
        for (i in 2022 downTo 1874) {
            listYear.add(i)
        }
        val arrayAdapterYear = ArrayAdapter(
            requireContext(),
            R.layout.item_popup_menu,
            listYear
        )

        listPopupWindow.setAdapter(arrayAdapterYear)

        // Set list popup's item click listener
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            year = listYear[position]

            Log.d(MovieRecomendationFragment::class.java.name, "position year: " + position)

            Log.d(
                MovieRecomendationFragment::class.java.name,
                "year  " + listYear[position].toString()
            )

            listPopupWindowButton.text = listYear.get(position).toString()

            // Dismiss popup.
            listPopupWindow.dismiss()
        }

        // Show list popup window on button click.
        listPopupWindowButton.setOnClickListener { v: View? ->
            listPopupWindow.show()


        }


    }


    private fun setupDefaultMovie() {

        viewModel.requestDefaultMovie()

        Log.d(MovieRecomendationFragment::class.java.name,"State list_movie - " + listDefaultMovie)

        viewModel.defaultMovie.observe(viewLifecycleOwner) {


            if (listDefaultMovie == null) {

                val random = (0..19).random()
                val movie = it.results[random]

                listDefaultMovie = mutableListOf(movie)



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

                movieID = movie.id.toInt()
                posterPath = movie.posterPath
                voteAverage = movie.voteAverage.toFloat()



            }

            else {

                val movie = listDefaultMovie!![listDefaultMovie!!.lastIndex]


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

                movieID = movie.id.toInt()
                posterPath = movie.posterPath
                voteAverage = movie.voteAverage.toFloat()



            }


        }

    }


    private fun setListenerButtonRequest() {

        binding.request.setOnClickListener {

            viewModel.requestMovie(year, genre)

        }

    }


    private fun toMovieDetail() {

        binding.cardView.setOnClickListener {

            val action = MovieRecomendationFragmentDirections.actionRandomToMovieDetail(
                movieID,
                posterPath,
                voteAverage
            )
            it.findNavController().navigate(action)

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(MovieRecomendationFragment::class.java.name,"onViewCreated")
    }


    override fun onStart() {
        super.onStart()
        Log.d(MovieRecomendationFragment::class.java.name,"onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d(MovieRecomendationFragment::class.java.name,"onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d(MovieRecomendationFragment::class.java.name,"onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d(MovieRecomendationFragment::class.java.name,"onStop")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(MovieRecomendationFragment::class.java.name,"onViewStateRestored")

    }



}







