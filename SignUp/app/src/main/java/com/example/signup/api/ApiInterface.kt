package com.example.signup.api

import com.example.signup.model.ListCity
import com.example.signup.model.ListDistrict
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("Cities.json")
    fun getCity() : Call<ListCity>

    @GET("Districts.json")
    fun getDistrict(): Call<ListDistrict>
}