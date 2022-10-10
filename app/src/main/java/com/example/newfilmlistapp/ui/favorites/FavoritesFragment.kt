package com.example.newfilmlistapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.newfilmlistapp.databinding.FragmentFavoritesBinding
import com.example.newfilmlistapp.databinding.FragmentPopularBinding
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.ui.popular.PopularAdapter
import com.example.newfilmlistapp.view_model.PopularViewModel


class FavoritesFragment : androidx.fragment.app.Fragment() {

    private var allMovies = arrayListOf<ResultPopular>()
    private var totalResults: Int = -1
    private var isLoading: Boolean = false

    private val viewModel: PopularViewModel by lazy {
        ViewModelProvider(this).get(PopularViewModel::class.java)
    }

    private val favoriteAdapter: PopularAdapter = PopularAdapter()
    //private val mScrollListener by lazy { RecyclerViewScrollListener(this) }



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