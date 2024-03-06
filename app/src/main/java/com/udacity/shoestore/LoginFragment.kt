package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

const val savedEmail = "mehedi@gmail.com"
const val savedPassword = "123456"

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()
                userLogin(email, password)
            }
        }

        return binding.root
    }

    private fun userLogin(email: String, password: String) {
        if (email == savedEmail && password == savedPassword) {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeScreenFragment)
        } else {
            Timber.d("email or password doesn't match! ")
        }


    }

}