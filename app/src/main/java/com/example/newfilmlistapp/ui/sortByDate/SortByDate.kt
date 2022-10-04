package com.example.newfilmlistapp.ui.sortByDate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.view_model.ViewModel_SortByDate
import com.example.newfilmlistapp.databinding.FragmentSortByDateBinding
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.model.MovieWrapper


class SortByDate : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentSortByDateBinding
    private val viewModel: ViewModel_SortByDate by lazy {
        ViewModelProvider(this).get(ViewModel_SortByDate::class.java)
    }
    private var movie_ID: Int = 0

    private lateinit var movieWrapper: MovieWrapper

    private lateinit var poster_path: String
    private var vote_average: Float = 0.0F



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
        binding = FragmentSortByDateBinding.inflate(inflater, container, false)

        workWithViewModel()
        initSpinners()
        setListenerButton()
        toMovieDetail()

        restoreSaveData()

        return binding.root
    }


    override fun onPause() {
        super.onPause()
    }




    fun workWithViewModel() {



        viewModel.genres
            .observe(viewLifecycleOwner)  {
                setupGenres(it.genres)
            }
        viewModel.movie.observe(viewLifecycleOwner) {

            movieWrapper = it

            val index = viewModel.array_index

            binding.textBelowPictureFilm.text =
                it?.results?.get(index)?.originalTitle ?: "75 string SortByDate"
            val tv_below_poster = it?.results?.get(index)?.originalTitle ?: "76 string SortByDate"

            val poster_path_local = it?.results?.get(index)?.posterPath ?: "77 string SortByDate"

            val vote_average_local = it?.results?.get(index)?.voteAverage


            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${poster_path_local}")
                .into(binding.pictureFilm)

            // movieID

            movie_ID = it.results.get(index).id.toInt()

            // Poster Path

            poster_path = poster_path_local

            // Vote Average

            vote_average = vote_average_local?.toFloat()!!





        }
    }

    private fun restoreSaveData() {

        if (viewModel.getYaerIndex != 0 && viewModel.getGenreIndex != 0) {

            binding.years.setSelection(viewModel.getYaerIndex)
            binding.genre.setSelection(viewModel.getGenreIndex)
            binding.textBelowPictureFilm.text =
                movieWrapper.results.get(viewModel.array_index).originalTitle

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${movieWrapper.results.get(viewModel.array_index).posterPath}")
                .into(binding.pictureFilm)


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

    fun initSpinners() {


        viewModel.requestGenres()


        // init spinner genres
        val listGenres = resources.getStringArray(R.array.genres)


        // init spinner years
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

        binding.common.setOnClickListener {

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

        binding.pictureFilm.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                val action = SortByDateDirections.actionRandomToMovieDetail(movie_ID,poster_path,vote_average)
                p0?.findNavController()?.navigate(action) ?: "191 string in SortByDate"

            }
        })

    }


}





