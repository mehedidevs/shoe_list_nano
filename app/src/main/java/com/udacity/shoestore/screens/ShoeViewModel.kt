package com.udacity.shoestore.screens

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

const val savedEmail = "mehedi@gmail.com"
const val savedPassword = "123456"

class ShoeViewModel : ViewModel() {


    val email = ObservableField<String>()
    val password = ObservableField<String>()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: MutableLiveData<Boolean>
        get() = _loginSuccess

    fun onLoginClicked() {
        val email = email.get()
        val password = password.get()
        Timber.d("$email $password")

        if (email == savedEmail && password == savedPassword) {
            _loginSuccess.postValue(true)
        } else {
            _loginSuccess.postValue(false)

        }
    }

    fun resetLogin() {
        loginSuccess.postValue(false)
    }


    private val shoes = mutableListOf(
        Shoe("Puma Sports", 1.0, "Puma", "this is a Puma Exclusive made shoe", listOf()),
        Shoe("Puma Casual", 2.0, "Puma", "this is a Puma Exclusive made shoe", listOf()),
        Shoe("Puma Formal", 3.0, "Puma", "this is a Puma Exclusive made shoe", listOf()),
        Shoe("Nike Sports", 2.0, "Nike", "this is a Nike Exclusive made shoe", listOf()),

        )


    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList


    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    init {
        _shoeList.postValue(shoes)
        getLatestShoe()
    }


    fun addShoe(shoe: Shoe) {
        shoes.add(shoe)
        _shoeList.postValue(shoes)
        getLatestShoe()
    }


    private fun getLatestShoe() {
        val shoe = shoes[shoes.size - 1]
        _shoe.postValue(shoe)

    }

}