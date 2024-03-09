package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.screens.ShoeViewModel
import timber.log.Timber


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val myViewModel: ShoeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.viewmodel = myViewModel
        binding.lifecycleOwner = this

        myViewModel.loginSuccess.observe(viewLifecycleOwner) {

            when (it) {
                true -> {
                    findNavController().navigate(R.id.action_loginFragment_to_welcomeScreenFragment)
                }

                false -> {
                    Timber.d("email or password doesn't match! ")
                }
            }

            myViewModel.resetLogin()

        }

        return binding.root
    }


}