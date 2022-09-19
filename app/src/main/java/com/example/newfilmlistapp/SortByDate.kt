package com.example.newfilmlistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newfilmlistapp.databinding.FragmentSortByDateBinding
import com.example.newfilmlistapp.model.Genres


class SortByDate : androidx.fragment.app.Fragment() {

    private lateinit var FragmentSortByDateBinding: FragmentSortByDateBinding



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