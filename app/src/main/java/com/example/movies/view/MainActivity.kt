package com.example.movies.view

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.example.movies.R.color.black
import com.example.movies.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Delay

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottomNavigation()
        setToolbar()

        Handler(Looper.getMainLooper()).postDelayed({
            binding.bottomNavigation.visibility = View.VISIBLE
            binding.toolbarMain.visibility = View.VISIBLE
            binding.appbarLayout.visibility = View.VISIBLE
        } , 3000)

    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbarMain)
    }

    private fun bottomNavigation() {

        binding.bottomNavigation.selectedItemId = R.id.homeFragment
        binding.toolbarMain.title = "Home"


        binding.bottomNavigation.setOnItemSelectedListener {



            when(it.itemId){

                R.id.downloadFragment ->{
                    binding.toolbarMain.title = "Download"
                }

                R.id.exploreFragment ->{
                    binding.toolbarMain.title = "Explore"
                }

                R.id.homeFragment ->{
                    binding.toolbarMain.title = "Home"
                }

                R.id.favoriteFragment ->{
                    binding.toolbarMain.title = "Favorite"
                }

                R.id.profileFragment ->{
                    binding.toolbarMain.title = "Profile"
                }


            }

            true
        }

    }
}