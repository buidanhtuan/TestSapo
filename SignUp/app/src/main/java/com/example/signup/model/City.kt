package com.example.signup.model

import com.google.gson.annotations.SerializedName

data class City(@SerializedName("Name") var name:String,
                @SerializedName("CityCode") var code: Int)
data class ListCity(@SerializedName("Cities") var listCity: ArrayList<City> )