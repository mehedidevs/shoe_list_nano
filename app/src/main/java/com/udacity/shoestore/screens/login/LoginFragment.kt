package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

const val savedEmail = "mehedi@gmail.com"
const val savedPassword = "123456"

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginFragment = this


        return binding.root
    }

    fun userLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        findNavController().navigate(R.id.action_loginFragment_to_welcomeScreenFragment)
        if (email == savedEmail && password == savedPassword) {

        } else {
            Timber.d("email or password doesn't match! ")
        }


    }

}