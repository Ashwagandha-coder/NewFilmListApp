package com.example.newfilmlistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import com.example.newfilmlistapp.databinding.FragmentSortByDateBinding
import com.example.newfilmlistapp.model.MovieWrapper


// todo: Проблемы с версткой

class SortByDate : androidx.fragment.app.Fragment() {

    private lateinit var fragmentSortByDateBinding: FragmentSortByDateBinding
    private val viewModel by lazy { ViewModelTMDB() }

    private lateinit var spinnerYear: Spinner
    private lateinit var spinnerGenres: Spinner


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSortByDateBinding = FragmentSortByDateBinding.inflate(inflater, container, false)

        initSpinners()
        workWithViewModel()

        return fragmentSortByDateBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    fun workWithViewModel() {

        viewModel.addDataInLiveData()

        viewModel.getInstanceLiveData().observe(viewLifecycleOwner, Observer<MovieWrapper> {})


    }

    fun initSpinners() {
        spinnerYear = fragmentSortByDateBinding.years
        spinnerGenres = fragmentSortByDateBinding.genre

        val arrayAdapterGenre = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf<String>("action", "comedy")
        )


        val listYear = mutableListOf<Int>()

        for (i in 1874..2022) {
            listYear.add(i)
        }


        val arrayAdapterYear = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listYear
        )

        fragmentSortByDateBinding.genre.adapter = arrayAdapterGenre

        fragmentSortByDateBinding.years.adapter = arrayAdapterYear


    }



}