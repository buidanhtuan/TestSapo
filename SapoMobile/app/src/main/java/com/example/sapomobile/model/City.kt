package com.example.sapomobile.model

import com.google.gson.annotations.SerializedName

object City {
    var CityName : String = ""
    var CityCode : Int = 0
}
data class CityData(@SerializedName("Name") var CityName:String,
                    @SerializedName("CityCode") var CityCode: Int)
data class ListCity(@SerializedName("Cities") var listCity: ArrayList<CityData> )