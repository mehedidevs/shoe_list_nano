package com.udacity.shoestore.screens.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.loginViewModel = myViewModel
        binding.lifecycleOwner = this


        setProgressObserver()
        setLoginObserver()






        return binding.root
    }

    private fun setLoginObserver() {
        myViewModel.userLoginData.observe(viewLifecycleOwner) {


            if (it.isLoggedIn) {
                findNavController().navigate(R.id.action_loginFragment_to_welcomeScreenFragment)

            } else {
                Timber.d("email or password doesn't match! ")
            }

            // Hide the keyboard.
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)


        }

    }

    private fun setProgressObserver() {
        myViewModel.progressState.observe(viewLifecycleOwner) {

            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }


        }


    }


}