package com.example.newfilmlistapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfilmlistapp.databinding.FragmentFavoritesBinding
import com.example.newfilmlistapp.view_model.FavoritesViewModel
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
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        workWithViewModel()
        setFragmentTitle()
        setRecyclerView()


        return binding.root
    }

    private fun workWithViewModel() {

        lifecycleScope.launch {
            viewModel.getListData.collect {
                favoriteAdapter.submitData(it)
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