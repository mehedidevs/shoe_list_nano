package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val shoes = mutableListOf(
        Shoe("Puma Sports", 1.0, "Puma", "this is a Puma Exclusive made shoe", listOf()),
        Shoe("Puma Casual", 2.0, "Puma", "this is a Puma Exclusive made shoe", listOf()),
        Shoe("Puma Formal", 3.0, "Puma", "this is a Puma Exclusive made shoe", listOf()),
        Shoe("Nike Sports", 2.0, "Nike", "this is a Nike Exclusive made shoe", listOf()),

        )

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList


    init {
        _shoeList.postValue(shoes)
    }


    fun addShoe(shoe: Shoe) {
        shoes.add(shoe)
        _shoeList.postValue(shoes)
    }


}