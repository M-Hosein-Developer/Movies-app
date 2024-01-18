package com.example.movies.view


import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.utils.NetworkChecker
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottomNavigation()
        setToolbar()
        networkChecker()


    }

    private fun networkChecker() {
        if (!NetworkChecker(this).internetConnection) {

            Snackbar.make(binding.root, "Check Internet connection", 5000).show()

        }
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbarMain)
    }

    private fun bottomNavigation() {

        binding.bottomNavigation.selectedItemId = R.id.homeFragment
        binding.toolbarMain.title = "Movies"


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = binding.bottomNavigation
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            if (destination.id == R.id.homeFragment ||
                destination.id == R.id.exploreFragment ||
                destination.id == R.id.downloadFragment ||
                destination.id == R.id.favoriteFragment ||
                destination.id == R.id.profileFragment) {

                val animBottom = TranslateAnimation(0f , 0f , 200f , +0f)
                animBottom.duration = 1000

                binding.bottomNavigation.startAnimation(animBottom)
                binding.view.startAnimation(animBottom)

                binding.bottomNavigation.visibility = View.VISIBLE
                binding.toolbarMain.visibility = View.VISIBLE
                binding.appbarLayout.visibility = View.VISIBLE

            }

            if (destination.id == R.id.videoPlayerFragment || destination.id == R.id.detailFragment) {

                binding.bottomNavigation.visibility = View.GONE
                binding.toolbarMain.visibility = View.VISIBLE
                binding.appbarLayout.visibility = View.VISIBLE

            }

        }
    }

}