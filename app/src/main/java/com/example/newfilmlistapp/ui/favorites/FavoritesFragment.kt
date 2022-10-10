package com.example.newfilmlistapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfilmlistapp.databinding.FragmentFavoritesBinding
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.ui.popular.PopularAdapter
import com.example.newfilmlistapp.view_model.FavoritesViewModel


class FavoritesFragment : androidx.fragment.app.Fragment() {

    private var allMovies = arrayListOf<ResultPopular>()
    private var totalResults: Int = -1
    private var isLoading: Boolean = false

    private val viewModel: FavoritesViewModel by viewModels()

    private val favoriteAdapter: FavoritesAdapter = FavoritesAdapter()
    //private val mScrollListener by lazy { RecyclerViewScrollListener(this) }

    private lateinit var binding: FragmentFavoritesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        setFragmentTitle()
        setRecyclerView()



        return binding.root
    }

    private fun workWithViewModel() {

        viewModel.favorite.observe(viewLifecycleOwner) {

            // todo: Данные submit либо сделать через flow

            favoriteAdapter


        }



    }


    private fun setRecyclerView() {

        binding.recyclerviewFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
        }

    }

    private fun setFragmentTitle() {

        val string = "Favorites Movie"

        binding.tViewInFavorites.apply {
            this.text = string
        }


    }



}