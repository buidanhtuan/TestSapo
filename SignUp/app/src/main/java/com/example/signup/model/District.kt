package com.example.signup.model

import com.google.gson.annotations.SerializedName

data class District(@SerializedName("CityCode") var cityCode: Int,
                    @SerializedName("Name") var name:String,
                    @SerializedName("DistrictCode") var code : Int)
data class ListDistrict(@SerializedName("Districts")var listDistrict: List<District>)