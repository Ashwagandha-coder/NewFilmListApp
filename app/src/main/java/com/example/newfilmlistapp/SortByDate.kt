package com.example.newfilmlistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.databinding.FragmentSortByDateBinding
import com.example.newfilmlistapp.model.Genres
import com.example.newfilmlistapp.model.MovieWrapper
import kotlinx.coroutines.launch


class SortByDate : androidx.fragment.app.Fragment() {

    private lateinit var fragmentSortByDateBinding: FragmentSortByDateBinding
    private val viewModel by lazy { ViewModelTMDB() }

    private lateinit var spinnerYear: Spinner
    private lateinit var spinnerGenres: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSpinners()
        workWithViewModel()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort_by_date, container, false)
    }


    fun workWithViewModel() {

        viewModel.addDataInLiveData()

        viewModel.getInstanceLiveData().observe(this,Observer<MovieWrapper> {})


    }

    fun initSpinners() {


        spinnerYear = fragmentSortByDateBinding.years
        spinnerGenres = fragmentSortByDateBinding.genre

        val arrayAdapter = ArrayAdapter.createFromResource(this.requireContext(),R.array.genres,android.R.layout.simple_spinner_item)



        arrayAdapter.also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinnerGenres.adapter = arrayAdapter


    }



}