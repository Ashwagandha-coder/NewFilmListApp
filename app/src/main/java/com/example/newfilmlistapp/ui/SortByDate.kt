package com.example.newfilmlistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.ViewModelTMDB
import com.example.newfilmlistapp.databinding.FragmentSortByDateBinding
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortByDateBinding.inflate(inflater, container, false)

        workWithViewModel()
        initSpinners()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    fun workWithViewModel() {

        viewModel.getInstanceLiveDataGenres()
            .observe(viewLifecycleOwner, Observer<GenresWrapper> { genresWrapper = it })
        viewModel.getInstanceLiveDataMovie().observe(viewLifecycleOwner, Observer<MovieWrapper> {})


    }

    fun initSpinners() {
        spinnerYear = binding.years
        spinnerGenres = binding.genre


        viewModel.requestGenres()


        // init spinner genres
        val listGenres = resources.getStringArray(R.array.genres)
        val arrayAdapterGenre = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listGenres
        )


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

        binding.genre.adapter = arrayAdapterGenre

        binding.years.adapter = arrayAdapterYear


    }


    fun setListenerButton() {

        binding.common.setOnClickListener { view ->

//            val year_spinner = setListenerSpinnerYear()
//            val genres_spinner = setListenerSpinnerGenres()


           // viewModel.requestMovie()

        }

    }


//    fun setListenerSpinnerGenres(): String? {
//
//        val result: String
//
//        spinnerGenres.setOnItemClickListener { adapterView, view, i, l ->
//
//            result = adapterView.adapter.getView(i).toString()
//
//        }
//
//        return result
//
//    }


    fun setListenerSpinnerYear(): Int? {

        var result: Int

        spinnerYear.onItemSelectedListener = OnItemSelectedListener

        return result


    }




}




