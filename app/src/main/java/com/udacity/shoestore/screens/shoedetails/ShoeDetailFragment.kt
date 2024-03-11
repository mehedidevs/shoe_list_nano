package com.udacity.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.screens.ShoeViewModel


class ShoeDetailFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailBinding

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.viewModel = shoeViewModel
        binding.lifecycleOwner = this
        binding.shoeDetailsFragment = this
        shoeViewModel.dataSave.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
                shoeViewModel.restSavedState()
            }
        }


        return binding.root
    }


    fun cancelTask() {
        findNavController().popBackStack()
    }


}