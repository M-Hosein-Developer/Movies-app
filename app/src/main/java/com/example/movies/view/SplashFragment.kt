package com.example.movies.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : DialogFragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.animeLottie.playAnimation()

        val pref = requireActivity().getSharedPreferences("Successful SignIn", Context.MODE_PRIVATE)
        val signIn = pref.getString("signIn" , "null")


        if (signIn == "successful"){
            lifecycleScope.launch {
                delay(3000)
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                binding.animeLottie.playAnimation()

            }
        }else{
            lifecycleScope.launch {
                delay(3000)
                findNavController().navigate(R.id.action_splashFragment_to_introFragment)
                binding.animeLottie.playAnimation()

            }
        }


    }


}