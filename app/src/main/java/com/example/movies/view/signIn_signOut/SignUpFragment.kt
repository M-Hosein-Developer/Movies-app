package com.example.movies.view.signIn_signOut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.databinding.FragmentSignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        signUpUser()

    }

    private fun signUpUser() {
        auth = Firebase.auth

        binding.btnSingUp.setOnClickListener {

            val email = binding.edtEmailSignUp.text.toString()
            val password = binding.edtPasswordSignUp.text.toString()
            val confirmPassword = binding.edtConfirmPasswordSignUp.text.toString()

            if (password == confirmPassword) {

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(requireContext(), "Successful registration", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)

                        } else {
                            Toast.makeText(requireContext(), "Registration not successful ", Toast.LENGTH_SHORT).show()
                        }
                    }

            }else{
                Toast.makeText(requireContext(), "The password is not the same", Toast.LENGTH_SHORT).show()
            }


        }


    }
}