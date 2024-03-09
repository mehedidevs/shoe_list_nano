package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {


    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.shoeListFragment = this
        observers()
        setTopMenu()



        return binding.root
    }


    private fun observers() {
        viewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
            displayShoes(shoeList)
        }
    }

    private fun displayShoes(shoes: List<Shoe>) {
        val layout = requireView().findViewById<LinearLayout>(R.id.shoe_list_layout)
        for (shoe in shoes) {
            val shoeItemView = layoutInflater.inflate(R.layout.item_shoe, layout, false)
            val shoeName = shoeItemView.findViewById<TextView>(R.id.txt_shoe_name)
            val shoeCompanyName = shoeItemView.findViewById<TextView>(R.id.txt_company_name)
            val shoeDescription = shoeItemView.findViewById<TextView>(R.id.txt_shoe_description)
            val shoeSize = shoeItemView.findViewById<TextView>(R.id.txt_shoe_size)
            shoe.apply {
                shoeName.text = name
                shoeCompanyName.text = company
                shoeDescription.text = description
                shoeSize.text = "$size"
            }

            layout.addView(shoeItemView)
        }
    }

    private fun setTopMenu() {
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoe_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_logout -> {
                        findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
                        Toast.makeText(activity, "logout...", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner)

    }

    fun goToShoeDetails() {
        findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
    }

}