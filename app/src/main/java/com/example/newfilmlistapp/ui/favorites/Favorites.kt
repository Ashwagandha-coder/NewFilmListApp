package com.example.newfilmlistapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newfilmlistapp.databinding.ActivityMainBinding
import com.example.newfilmlistapp.databinding.FragmentFavoritesBinding
import com.example.newfilmlistapp.databinding.FragmentPopularBinding
import com.example.newfilmlistapp.ui.popular.PopularAdapter
import com.example.newfilmlistapp.ui.recycler_view.RecyclerViewScrollListener


class Favorites : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var favoriteAdapter: Any
    private lateinit var mScrollListener: RecyclerViewScrollListener


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setRecyclerView() {}

    private fun setFragmentTitle() {}




}