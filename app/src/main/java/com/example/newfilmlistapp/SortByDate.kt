package com.example.newfilmlistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.databinding.FragmentSortByDateBinding
import com.example.newfilmlistapp.model.Genres
import kotlinx.coroutines.launch


class SortByDate : androidx.fragment.app.Fragment() {

    private lateinit var FragmentSortByDateBinding: FragmentSortByDateBinding

    private lateinit var viewModelTMDB: ViewModelTMDB

    private val viewModel by lazy { ViewModelTMDB() }



    override fun onCreate(savedInstanceState: Bundle?) {


        for(i in 0..10){

        }

        val genre = Genres(1,"Action")
        Log.d("SortByDate","result: ${genre}")

        super.onCreate(savedInstanceState)







    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort_by_date, container, false)
    }



}