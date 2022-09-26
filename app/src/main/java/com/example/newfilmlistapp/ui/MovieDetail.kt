package com.example.newfilmlistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.newfilmlistapp.ViewModelTMDB
import com.example.newfilmlistapp.databinding.FragmentMovieDetailBinding


class MovieDetail : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: ViewModelTMDB by lazy {
        ViewModelProvider(this).get(ViewModelTMDB::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)

        onBackScreen()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun onBackScreen() {

        binding.topNavBar.returnImageView.setOnClickListener {

            val action = MovieDetailDirections.actionMovieDetailToRandom()
            it.findNavController().navigate(action)

        }

    }

    fun workWithViewModel() {



    }




}