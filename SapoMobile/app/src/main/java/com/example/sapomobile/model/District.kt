package com.example.sapomobile.model

import com.google.gson.annotations.SerializedName

object District {
    var DistrictName : String = ""
    var DistrictCode : Int = 0
}

/**
 * format jog city
 */
data class DistrictData(@SerializedName("CityCode") var CityCode: Int,
                        @SerializedName("Name") var DistrictName:String,
                        @SerializedName("DistrictCode") var DistrictCode: Int)
data class ListDistrict(@SerializedName("Districts")var listDistrict: ArrayList<DistrictData>)