package com.example.sapomobile.model

object District {
    var DistrictName : String = ""
    var DistrictCode : Int = 0
}
data class DistrictData(var CityCode : Int = 0, var DistrictName : String = "", var DistrictCode : Int = 0)
data class ListDistrict(var listDistrict: ArrayList<DistrictData>)