package com.example.newfilmlistapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfilmlistapp.databinding.ActivityMainBinding
import com.example.newfilmlistapp.databinding.FragmentFavoritesBinding
import com.example.newfilmlistapp.databinding.FragmentPopularBinding
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.ui.popular.PopularAdapter
import com.example.newfilmlistapp.ui.recycler_view.RecyclerViewScrollListener
import com.example.newfilmlistapp.ui.recycler_view.ScrollBack
import com.example.newfilmlistapp.view_model.ViewModel_Favorites
import com.example.newfilmlistapp.view_model.ViewModel_Popular


class Favorites : androidx.fragment.app.Fragment(), ScrollBack {

    private lateinit var binding: FragmentFavoritesBinding

    private var allMovies = arrayListOf<ResultPopular>()
    private var totalResults: Int = -1
    private var isLoading: Boolean = false

    private val viewModel: ViewModel_Favorites by lazy {
        ViewModelProvider(this).get(ViewModel_Favorites::class.java)
    }

    private val favoritesAdapter: PopularAdapter = PopularAdapter()
    private val mScrollListener by lazy { RecyclerViewScrollListener(this) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        workWithViewModel()
        setRecyclerView()
        setFragmentTitle()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun workWithViewModel() {

       // viewModel.loadData(1)
        viewModel.favorite.observe(viewLifecycleOwner) {

            totalResults = it.totalResults.toInt()
            allMovies.addAll(it.results)
            favoritesAdapter.submitList(allMovies)
            isLoading = false

        }

    }

    private fun setRecyclerView() {
        binding.recyclerviewFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
            addOnScrollListener(mScrollListener)
        }
    }

    private fun setFragmentTitle() {
        val string = "Favorites"
        binding.tViewInFavorites.apply {
            text = string
        }
    }


    override fun onScrollCompleted(firstVisibleItem: Int, isLoadingMoreData: Boolean) {
        if (allMovies.size != totalResults) {
            if (!isLoading) {
                isLoading = true
                //viewModel.loadData(1)
            }
        }
    }




}