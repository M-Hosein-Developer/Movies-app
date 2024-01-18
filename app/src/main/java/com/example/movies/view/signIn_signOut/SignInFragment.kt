package com.example.movies.view.signIn_signOut

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.databinding.FragmentSignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class SignInFragment : Fragment() {

    private lateinit var binding : FragmentSignInBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       binding = FragmentSignInBinding.inflate(layoutInflater , container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInUser()

    }

    private fun signInUser() {
        auth = Firebase.auth

        binding.btnSingIn.setOnClickListener {

            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(requireContext(), "Successful SignIn", Toast.LENGTH_SHORT).show()


                        val pref = requireActivity().getSharedPreferences("Successful SignIn", Context.MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putString("signIn","successful")
                        editor.apply()

                        findNavController().navigate(R.id.action_signInFragment_to_homeFragment)

                    } else {

                        Toast.makeText(requireContext(), "Check Email or Password ", Toast.LENGTH_SHORT).show()

                    }
                }


        }


    }

}