package com.example.signup.utils

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.signup.MainActivity
import com.example.signup.screen.fragment.CityFragment
import java.text.FieldPosition

fun saveCityPosition (position: Int,activity : FragmentActivity){
    val infoCache = activity?.getSharedPreferences("infoUser", Context.MODE_PRIVATE)
    val editor = infoCache?.edit()
    editor?.putInt("cityPosition", position)
    editor?.apply()
}
fun saveDistrictPosition (position: Int,activity : FragmentActivity){
    val infoCache = activity?.getSharedPreferences("infoUser", Context.MODE_PRIVATE)
    val editor = infoCache?.edit()
    editor?.putInt("districtPosition", position)
    editor?.apply()
}
fun saveCityCode(CityCode: Int,activity : FragmentActivity){
    val infoCache = activity?.getSharedPreferences("infoUser", Context.MODE_PRIVATE)
    val editor = infoCache?.edit()
    editor?.putInt("cityCode", CityCode)
    editor?.apply()
}
fun deleteShared(activity: FragmentActivity){
    val infoCache = activity?.getSharedPreferences("infoUser", Context.MODE_PRIVATE)
    val editor = infoCache?.edit()
    editor?.putInt("cityCode", 0)
    editor?.putInt("districtPosition", 0)
    editor?.putInt("cityPosition", 0)

}