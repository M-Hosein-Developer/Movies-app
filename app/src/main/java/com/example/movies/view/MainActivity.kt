package com.example.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.model.Repository
import com.example.movies.model.apiService.ApiService
import com.example.movies.model.room.MoviesList
import com.example.movies.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val viewModel : MainViewModel by viewModels()

    @Inject
    lateinit var apiService: ApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        lifecycleScope.launch {
//            viewModel.getAllData(Repository(apiService)).enqueue(object : Callback<MoviesList>{
//                override fun onResponse(
//                    call: Call<MoviesList>,
//                    response: Response<MoviesList>
//                ) {
//
//                    val a = response.body()
//                    Log.v("testDAta" , a.toString())
//
//                }
//
//                override fun onFailure(call: Call<MoviesList>, t: Throwable) {
//                    Log.v("testDAta" , t.message!!)
//                }
//
//            })
//        }



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


        binding.bottomNavigation.setOnItemSelectedListener {

            val navController = this.findNavController(R.id.fragmentContainerView)

            val navView: BottomNavigationView = binding.bottomNavigation

            navView.setupWithNavController(navController)

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