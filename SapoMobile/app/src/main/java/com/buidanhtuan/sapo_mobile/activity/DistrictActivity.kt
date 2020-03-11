package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.buidanhtuan.sapo_mobile.interfaces.OnClickItemListener
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.adapter.DistrictAdapter
import com.buidanhtuan.sapo_mobile.input.ValueDistrict
import kotlinx.android.synthetic.main.activity_district.*

class DistrictActivity : AppCompatActivity(), OnClickItemListener {
    private val listDistrict: ArrayList<District> = ArrayList()
    class District{
        var cityCode : Int = 0
        var districtName : String = ""
        var districtCode : Int = 0
    }
    companion object {
        var districtName : String = ""
        var districtCode : Int = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district)
        rv_district.layoutManager = LinearLayoutManager(this)
        rv_district.adapter = DistrictAdapter(listDistrict, this)
        val value = ValueDistrict(listDistrict,rv_district)
        value.Json().execute()
    }
    override fun onClickItem(position: Int) {
        districtName = listDistrict[position].districtName
        districtCode = listDistrict[position].districtCode
        val intent = Intent (this, AgeActivity::class.java)
        rv_district.adapter = DistrictAdapter(listDistrict,this)
        if(districtCode != -1){
            (rv_district.layoutManager as LinearLayoutManager).scrollToPosition(position)
        }
        startActivity(intent)
    }
}