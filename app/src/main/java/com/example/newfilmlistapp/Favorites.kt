package com.example.newfilmlistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newfilmlistapp.databinding.ActivityMainBinding
import com.example.newfilmlistapp.databinding.FragmentFavoritesBinding
import com.example.newfilmlistapp.databinding.FragmentPopularBinding


class Favorites : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentFavoritesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoritesBinding.inflate(layoutInflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




}