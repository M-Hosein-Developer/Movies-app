package com.example.movies.view.signIn_signOut

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {

    private lateinit var binding : FragmentIntroBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentIntroBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.signIn.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_signInFragment)
        }


        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_signUpFragment)
        }

    }

}