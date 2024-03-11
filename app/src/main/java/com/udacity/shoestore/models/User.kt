package com.udacity.shoestore.models

import android.util.Patterns


data class User(
    val email: String,
    val password: String,
    var isLoggedIn: Boolean = false

)

fun String.isEmailValid(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isPasswordValidLength(): Boolean {
    return length > 5
}

