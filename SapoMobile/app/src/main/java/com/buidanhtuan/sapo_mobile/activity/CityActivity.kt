package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.buidanhtuan.sapo_mobile.interfaces.OnClickItemListener
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.adapter.CityAdapter
import com.buidanhtuan.sapo_mobile.input.ValueCity
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity : AppCompatActivity(), OnClickItemListener {
    private val listCity: ArrayList<City> = ArrayList()
    class City {
        var cityName = ""
        var cityCode = 0
    }
    companion object {
        var cityName = ""
        var cityCode = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        rv_city.layoutManager = LinearLayoutManager(this)
        rv_city.adapter = CityAdapter(listCity, this)
        val value = ValueCity(listCity,rv_city)
        value.Json().execute()
    }
    override fun onClickItem(position: Int) {
        val intent = Intent (this, DistrictActivity::class.java)
        cityName = listCity[position].cityName
        cityCode = listCity[position].cityCode
        rv_city.adapter = CityAdapter(listCity,this)
        if(cityCode != -1){
            (rv_city.layoutManager as LinearLayoutManager).scrollToPosition(position)
        }
        startActivity(intent)
    }
}
