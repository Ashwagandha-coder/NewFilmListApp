package com.example.newfilmlistapp.ui.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfilmlistapp.view_model.PopularViewModel
import com.example.newfilmlistapp.databinding.FragmentPopularBinding
import com.example.newfilmlistapp.model.ResultPopular
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PopularFragment : Fragment() {

    // field

    private var allMovies = arrayListOf<ResultPopular>()
    private var totalResults: Int = -1
    private var isLoading: Boolean = false

    private lateinit var binding: FragmentPopularBinding

    private val viewModel: PopularViewModel by lazy {
        ViewModelProvider(this).get(PopularViewModel::class.java)
    }

    private val popularAdapter: PopularAdapter = PopularAdapter()
    //private val mScrollListener by lazy { RecyclerViewScrollListener(this) }


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


    fun workWithViewModel() {
        viewModel.requestPopular()


        viewModel.popularMovie.observe(viewLifecycleOwner) {

            totalResults = it.totalResults.toInt()
            allMovies.addAll(it.results)
//            popularAdapter.submitAll(allMovies)
            isLoading = false

        }


    }

    private fun setRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = popularAdapter
            //addOnScrollListener(mScrollListener)
        }
    }

    private fun setFragmentTitle() {
        val string = "Popular Movie"
        binding.tView.apply {
            text = string
        }
    }


    private fun toMovieDetail() {


    }


}