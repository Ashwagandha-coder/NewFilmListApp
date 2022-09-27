package com.example.newfilmlistapp.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newfilmlistapp.view_model.ViewModel_Popular
import com.example.newfilmlistapp.databinding.FragmentPopularBinding


class Popular : Fragment() {

    private lateinit var binding: FragmentPopularBinding

    private val viewModel: ViewModel_Popular by lazy {
        ViewModelProvider(this).get(ViewModel_Popular::class.java)
    }

    //    private val popular_adapter
//    private val mScrollListener


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater,container,false)


        viewModel.requestPopular()

        return binding.root
    }


    fun workWithViewModel() {

        viewModel.popularMovie.observe(viewLifecycleOwner) {




        }


    }







}