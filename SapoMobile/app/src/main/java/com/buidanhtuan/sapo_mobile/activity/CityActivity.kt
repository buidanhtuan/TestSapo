package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.sapo_mobile.Interface.OnClickItemListener
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.adapter.CityAdapter
import com.buidanhtuan.sapo_mobile.input.ValueCity
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.adapter_city.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.net.URLConnection

class CityActivity : AppCompatActivity(), OnClickItemListener {
    val listCity: ArrayList<City> = ArrayList()
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
        var value = ValueCity(listCity,rv_city) //warming
        value.Json().execute()
    }
    override fun onClickItem(position: Int) {
        val intent: Intent = Intent (this, DistrictActivity::class.java)
        cityName = listCity.get(position).cityName
        cityCode = listCity.get(position).cityCode
        rv_city.adapter = CityAdapter(listCity,this)
        if(cityCode != -1){
            (rv_city.layoutManager as LinearLayoutManager).scrollToPosition(position)
        }
        startActivity(intent)
    }
}
