package com.example.signup.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Account(var name: String,
                   var email : String,
                   var password : String,
                   var city : String,
                   var district : String,
                   var age : Int,
                   var sex : String): Parcelable {
    constructor() : this("", "", "", "","",25,"nam")
}