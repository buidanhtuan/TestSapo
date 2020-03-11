package com.example.sapomobile.api

import com.example.sapomobile.model.ListCity
import com.example.sapomobile.model.ListDistrict
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("Cities.json")
    fun getCity() : Call<ListCity>

    @GET("Districts.json")
    fun getDistrict(): Call<ListDistrict>
}