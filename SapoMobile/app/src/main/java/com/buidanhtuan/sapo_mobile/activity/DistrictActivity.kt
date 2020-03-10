package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.buidanhtuan.sapo_mobile.Interface.OnClickItemListener
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.adapter.CityAdapter
import com.buidanhtuan.sapo_mobile.adapter.DistrictAdapter
import com.buidanhtuan.sapo_mobile.input.ValueDistrict
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.activity_district.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.net.URLConnection

class DistrictActivity : AppCompatActivity(), OnClickItemListener {
    val listDistrict: ArrayList<District> = ArrayList()
    class District{
        var cityCode : Int = 0
        var districtName : String = ""
        var districtCode : Int = 0
    }
    companion object {
        var cityCode : Int = 0
        var districtName : String = ""
        var districtCode : Int = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district)
        rv_district.layoutManager = LinearLayoutManager(this)
        rv_district.adapter = DistrictAdapter(listDistrict, this)
        var value = ValueDistrict(listDistrict,rv_district)
        value.Json().execute()
    }
    override fun onClickItem(position: Int) {
        districtName = listDistrict.get(position).districtName
        districtCode = listDistrict.get(position).districtCode
        val intent: Intent = Intent (this, AgeActivity::class.java)
        rv_district.adapter = DistrictAdapter(listDistrict,this)
        if(districtCode != -1){
            (rv_district.layoutManager as LinearLayoutManager).scrollToPosition(position)
        }
        startActivity(intent)
    }
}