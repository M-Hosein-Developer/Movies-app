package com.example.movies.view


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.utils.NetworkChecker
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

            if (destination.id == R.id.homeFragment) {

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