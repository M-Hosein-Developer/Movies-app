package com.example.movies.view


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.model.apiService.ApiService
import com.example.movies.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottomNavigation()
        setToolbar()

        lifecycleScope.launch{
            delay(4000)
            binding.bottomNavigation.visibility = View.VISIBLE
            binding.toolbarMain.visibility = View.VISIBLE
            binding.appbarLayout.visibility = View.VISIBLE
        }

    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbarMain)
    }

    private fun bottomNavigation() {

        binding.bottomNavigation.selectedItemId = R.id.homeFragment
        binding.toolbarMain.title = "Home"


        val navController = this.findNavController(R.id.fragmentContainer)
        val navView: BottomNavigationView = binding.bottomNavigation
        navView.setupWithNavController(navController)

    }
}