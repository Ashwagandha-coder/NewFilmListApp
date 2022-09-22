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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.mainRoot)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }

//    private fun observeViewModel() {
//
//        viewModel = ViewModelTMDB()
//
//
//    }


//    fun chooseItemMenu(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//
//            R.id.sortByDate -> { fragmentManager.beginTransaction().hide(active).commit()
//                active = sortByDate
//
//                return true
//
//            }
//
//            R.id.popular -> { fragmentManager.beginTransaction().hide(active).commit()
//                active = popular
//
//                return true
//
//
//            }
//
//            R.id.favorites -> { fragmentManager.beginTransaction().hide(active).commit()
//                active = favorites
//
//                return true
//
//            }
//
//        }
//
//        return false
//
//    }


}