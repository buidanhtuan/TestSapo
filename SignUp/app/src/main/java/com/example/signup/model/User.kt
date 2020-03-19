package com.example.signup.model

data class User(
    internal var name: String,
    var email : String,
    var password : String,
    var repeat : String)