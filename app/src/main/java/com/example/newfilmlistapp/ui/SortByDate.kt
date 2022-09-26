package com.example.newfilmlistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.ViewModelTMDB
import com.example.newfilmlistapp.databinding.FragmentSortByDateBinding
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.model.GenresWrapper
import com.example.newfilmlistapp.model.MovieWrapper


// todo: Проблемы с версткой

class SortByDate : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentSortByDateBinding
    private val viewModel: ViewModelTMDB by lazy {
        ViewModelProvider(this).get(ViewModelTMDB::class.java)
    }

    private lateinit var spinnerYear: Spinner
    private lateinit var spinnerGenres: Spinner


    private val collection: List<String> by lazy { mutableListOf() }
    private lateinit var genresWrapper: GenresWrapper
    private var movieWrapper: MovieWrapper? = null

    lateinit var stringTest: String


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

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    fun workWithViewModel() {

        viewModel.getInstanceLiveDataGenres()
            .observe(viewLifecycleOwner, Observer<GenresWrapper> {
                setupGenres(it.genres)
            })
        viewModel.movie.observe(viewLifecycleOwner) {
//                movieWrapper = it
            binding.textBelowPictureFilm.text =
                it?.results?.firstOrNull()?.originalTitle ?: "128 string SortByDate"

            val poster_path: String? = it?.results?.firstOrNull()?.posterPath

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${poster_path}")
                .into(binding.pictureFilm)

        }
    }


    private fun setupGenres(genres: List<Genres>) {
        val arrayAdapterGenre = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            genres.map { it.name }
        )
        binding.genre.adapter = arrayAdapterGenre

    }

    fun initSpinners() {
        spinnerYear = binding.years
        spinnerGenres = binding.genre


        viewModel.requestGenres()


        // init spinner genres
        val listGenres = resources.getStringArray(R.array.genres)


        // init spinner years
        val listYear = mutableListOf<Int>()

        for (i in 1874..2022) {
            listYear.add(i)
        }


        val arrayAdapterYear = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listYear
        )

        binding.years.adapter = arrayAdapterYear


    }

    // todo: При нажатии на кнопку приложение крашится lateinit property хотя оно подписано на лив дату


    fun setListenerButton() {

        binding.common.setOnClickListener {

            val year = getItemSpinnerYear()
            val genre = getItemSpinnerGenre()

            viewModel.requestMovie(year, genre)


        }

    }


    fun getItemSpinnerGenre(): Int {

        var result: Int = 0

        spinnerGenres.onItemSelectedListener = object : OnItemSelectedListener {
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

        spinnerYear.onItemSelectedListener = object : OnItemSelectedListener {
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

                val action = SortByDateDirections.actionRandomToMovieDetail()
                p0?.findNavController()?.navigate(action) ?: "191 string in SortByDate"

            }
        })

    }


}


//fun main() {
//
//    val sortByDate = SortByDate()
//
//
//    sortByDate.workWithViewModel()
//    sortByDate.initSpinners()
//    sortByDate.setListenerButton()
//
//    val result = sortByDate.stringTest
//
//    println(result)
//
//}




