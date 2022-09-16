package com.example.newfilmlistapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.newfilmlistapp.network.LoadingMovieDBService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


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

        val moshi = Moshi.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val loadingMovieDBService =  retrofit.create(LoadingMovieDBService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            try {
                val result = loadingMovieDBService.getGenres()
                Log.d("Test","result: ${result.genres}")

            } catch (e: Exception){
                Log.e("error", "fetch movie error $e")
            }

        }

    }


//    fun init() {
//
//
//        sortByDate = SortByDate()
//        popular = Popular()
//        favorites = Favorites()
//
//        active = sortByDate
//
//
//        variable = BottomNavigationView.OnNavigationItemSelectedListener { chooseItemMenu(it) }
//
//    }
//
//
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
//
//
//
//    fragmentManager = supportFragmentManager




}