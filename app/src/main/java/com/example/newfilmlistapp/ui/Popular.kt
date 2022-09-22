package com.example.newfilmlistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newfilmlistapp.databinding.FragmentPopularBinding


class Popular : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentPopularBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPopularBinding.inflate(layoutInflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }






}