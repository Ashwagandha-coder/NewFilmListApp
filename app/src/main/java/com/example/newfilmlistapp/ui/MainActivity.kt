package com.example.newfilmlistapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newfilmlistapp.*
import com.example.newfilmlistapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    private lateinit var sortByDate: SortByDate
    private lateinit var popular: Popular
    private lateinit var favorites: Favorites

    private lateinit var fragmentManager: FragmentManager

    private lateinit var active: Fragment
    private lateinit var variable: BottomNavigationView.OnNavigationItemSelectedListener
    private lateinit var viewModel: ViewModelTMDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

       // init()


    }

//    private fun observeViewModel() {
//
//        viewModel = ViewModelTMDB()
//
//
//    }

    fun init() {

        fragmentManager = supportFragmentManager

        sortByDate = SortByDate()
        popular = Popular()
        favorites = Favorites()

        active = sortByDate

        variable = BottomNavigationView.OnNavigationItemSelectedListener { chooseItemMenu(it) }



    }


    fun chooseItemMenu(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.sortByDate -> { fragmentManager.beginTransaction().hide(active).commit()
                active = sortByDate

                return true

            }

            R.id.popular -> { fragmentManager.beginTransaction().hide(active).commit()
                active = popular

                return true


            }

            R.id.favorites -> { fragmentManager.beginTransaction().hide(active).commit()
                active = favorites

                return true

            }

        }

        return false

    }




}