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
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.shoelist.ShoeViewModel


class ShoeDetailFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailBinding

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.viewModel = shoeViewModel
        binding.lifecycleOwner = this
        setListener()
        return binding.root
    }

    private fun setListener() {

        binding.apply {
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
            btnSave.setOnClickListener {
                val name = etShoeName.text.toString().trim()
                val company = etCompany.text.toString().trim()
                val description = etDescription.text.toString().trim()
                val size = etShoeSize.text.toString().trim()
                val shoe = Shoe(name, size.toDouble(), company, description)
                shoeViewModel.addShoe(shoe)
                findNavController().popBackStack()


            }


        }


    }


}