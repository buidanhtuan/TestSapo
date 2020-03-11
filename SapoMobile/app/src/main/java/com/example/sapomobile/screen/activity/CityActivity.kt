package com.example.sapomobile.screen.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sapomobile.R
import com.example.sapomobile.api.ApiClient
import com.example.sapomobile.api.ApiInterface
import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.City
import com.example.sapomobile.model.CityData
import com.example.sapomobile.model.ListCity
import com.example.sapomobile.screen.adapter.CityAdapter
import kotlinx.android.synthetic.main.activity_city.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CityActivity : AppCompatActivity(), OnClickItemListener{
    private val apiClient = ApiClient().getClient()?.create(ApiInterface::class.java)
    var listCity : ArrayList<CityData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        rv_city.layoutManager = LinearLayoutManager(this)
        getCity(this)
    }
    override fun onClickItem(position: Int) {
        val intent = Intent (this, DistrictActivity::class.java)
        City.CityName = listCity[position].CityName
        City.CityCode = listCity[position].CityCode
        startActivity(intent)
    }
    private fun getCity(c : Context){
        apiClient?.getCity()?.enqueue(object : Callback<ListCity>{
            override fun onResponse(call: Call<ListCity>, response: Response<ListCity>) {
                val city = response.body()
                val list = city?.listCity
                rv_city.adapter = list?.let { CityAdapter(it, c as CityActivity) }
                if (list != null) {
                    listCity = list
                }
            }
            override fun onFailure(call: Call<ListCity>, t: Throwable) {}
        })
    }
}

