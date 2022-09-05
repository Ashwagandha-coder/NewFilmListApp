package com.example.newfilmlistapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var sortByDate: SortByDate
    private lateinit var popular: Popular
    private lateinit var favorites: Favorites

    private lateinit var fragmentManager: FragmentManager

    private lateinit var active: Fragment

    private lateinit var variable: BottomNavigationView.OnNavigationItemSelectedListener




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


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