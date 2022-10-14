package com.example.newfilmlistapp.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.newfilmlistapp.app.FilmListApp
import com.example.newfilmlistapp.databinding.FragmentFavoritesBinding
import com.example.newfilmlistapp.local.db.AppDatabase
import com.example.newfilmlistapp.view_model.favorite.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FavoritesFragment : androidx.fragment.app.Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()
    private val favoriteAdapter: FavoritesAdapter = FavoritesAdapter()
    private lateinit var binding: FragmentFavoritesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        workWithViewModel()
        setFragmentTitle()
        setRecyclerView()


        return binding.root
    }

    private fun workWithViewModel() {
        val room = Room.databaseBuilder(requireContext().applicationContext, AppDatabase::class.java,"Movie_DB").build()

        lifecycleScope.launch {

            try {
//                favoriteAdapter.loadStateFlow.collectLatest {
//                    when (it.refresh) {
//                        is LoadState.Loading -> Log.d("FavoritesFragment", "Loading")
//                        is LoadState.NotLoading -> Log.d("FavoritesFragment", "NotLoading")
//                        is LoadState.Error -> Log.d("FavoritesFragment", "Error")
//                    }
//
//                }

                viewModel.listDataFavorite(room).collect {
                    favoriteAdapter.submitData(it)
                }
            } catch (e: Exception) {
                Log.e("FavoritesFragment", "workWithViewModel error ${e}")
            }
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