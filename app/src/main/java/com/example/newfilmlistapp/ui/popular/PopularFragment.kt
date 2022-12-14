package com.example.newfilmlistapp.ui.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfilmlistapp.view_model.popular.PopularViewModel
import com.example.newfilmlistapp.databinding.FragmentPopularBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PopularFragment : Fragment() {


    private lateinit var binding: FragmentPopularBinding
    private val viewModel: PopularViewModel by viewModels()
    private val popularAdapter: PopularAdapter = PopularAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)

        workWithViewModel2()
        setFragmentTitle()
        setRecyclerView()

        return binding.root
    }

    private fun workWithViewModel2() {

        lifecycleScope.launch {
            popularAdapter.loadStateFlow.collectLatest {
                Log.d("frag", "loadStateFlow ${it}")
                when(it.refresh){
                    LoadState.Loading -> Log.d("frag", "Loading")
                    LoadState.Error(Exception()) -> Log.d("frag", "Error")
                    LoadState.NotLoading(false) -> Log.d("frag", "NotLoading")
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewModel.getListData.collect {
                popularAdapter.submitData(it)
            }
        }

    }

    private fun setRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = popularAdapter
        }
    }

    private fun setFragmentTitle() {
        val string = "Popular Movie"
        binding.tView.apply {
            text = string
        }
    }

}