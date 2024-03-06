package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentWelcomeScreenBinding


class WelcomeScreenFragment : Fragment() {


    lateinit var binding: FragmentWelcomeScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_screen, container, false)

        binding.btnInstructions.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_instructionFragment)
        }


        return binding.root
    }


}