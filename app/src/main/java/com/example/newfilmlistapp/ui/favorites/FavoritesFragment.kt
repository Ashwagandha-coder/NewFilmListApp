package com.example.newfilmlistapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newfilmlistapp.databinding.FragmentFavoritesBinding


class FavoritesFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentFavoritesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        return binding.root
    }


    private fun setRecyclerView() {

        binding.recyclerviewFavorites



    }

    private fun setFragmentTitle() {

        val string = "Favorites Movie"

        binding.tViewInFavorites.apply {
            this.text = string
        }


    }



}