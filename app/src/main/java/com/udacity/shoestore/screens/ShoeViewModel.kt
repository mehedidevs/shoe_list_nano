package com.udacity.shoestore.screens

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User
import com.udacity.shoestore.models.isEmailValid
import com.udacity.shoestore.models.isPasswordValidLength


const val savedEmail = "mehedi@gmail.com"
const val savedPassword = "123456"

class ShoeViewModel : ViewModel() {


    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var errorPassword = MutableLiveData<String>()
    var errorEmail = MutableLiveData<String>()


    private var _userLoginData: MutableLiveData<User> = MutableLiveData<User>()
    val userLoginData: LiveData<User>
        get() = _userLoginData


    private val _progressState = MutableLiveData<Boolean>(false)
    val progressState: LiveData<Boolean>
        get() = _progressState


    fun onLoginClicked() {
        _progressState.postValue(true)

        Handler(Looper.getMainLooper()).postDelayed({
            val user = User(
                email.getValue().toString(),
                password.getValue().toString()
            )
            if (user.email.isEmpty()) {
                errorEmail.postValue("Enter an email address!")

            } else if (!user.email.isEmailValid()) {
                errorEmail.postValue("Enter a valid email address")

            } else if (user.password.isEmpty()) {
                errorPassword.postValue("Enter your password!")

            } else if (!user.password.isPasswordValidLength()) {
                errorPassword.postValue("Password Length should be 6 or more !")

            } else {
                user.isLoggedIn = user.email == savedEmail && user.password == savedPassword
                _userLoginData.postValue(user)


            }
            _progressState.postValue(false)


        }, 3000)


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
    val etName = MutableLiveData<String>()
    val etSize = MutableLiveData<String>()
    val etCompany = MutableLiveData<String>()
    val etDescription = MutableLiveData<String>()


    init {
        _shoeList.postValue(shoes)
        getLatestShoe()
    }

    private val _dataSaved = MutableLiveData<Boolean>(false)
    val dataSave: LiveData<Boolean>
        get() = _dataSaved


    fun addShoe() {
        val shoe = Shoe(
            etName.value.toString(),
            etSize.value?.toDouble() ?: 0.0,
            etCompany.value.toString(),
            etDescription.value.toString(),
            emptyList()
        )


        shoes.add(shoe)
        _shoeList.postValue(shoes)

        _dataSaved.postValue(true)
    }

    fun restSavedState(){
        _dataSaved.postValue(false)
    }


    private fun getLatestShoe() {
        val shoe = shoes[shoes.size - 1]
        _shoe.postValue(shoe)
        shoe.apply {
            etName.postValue(name)
            etSize.postValue("$size")
            etDescription.postValue(description)
            etCompany.postValue(company)
        }

    }

}