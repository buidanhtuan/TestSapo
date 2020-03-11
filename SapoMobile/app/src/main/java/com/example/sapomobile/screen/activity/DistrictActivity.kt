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
import com.example.sapomobile.model.District
import com.example.sapomobile.model.DistrictData
import com.example.sapomobile.model.ListDistrict
import com.example.sapomobile.screen.adapter.DistrictAdapter
import kotlinx.android.synthetic.main.activity_district.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DistrictActivity : AppCompatActivity(), OnClickItemListener {
    private val apiClient = ApiClient().getClient()?.create(ApiInterface::class.java)
    private var listDistrict: ArrayList<DistrictData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district)
        rv_district.layoutManager = LinearLayoutManager(this)
        getDistrict(this)
    }
    override fun onClickItem(position: Int) {
        District.DistrictName = listDistrict[position].DistrictName
        District.DistrictCode = listDistrict[position].DistrictCode
        val intent = Intent (this, AgeActivity::class.java)
        startActivity(intent)
    }
    private fun getDistrict(c : Context){
        apiClient?.getDistrict()?.enqueue(object : Callback<ListDistrict>{
            override fun onResponse(call: Call<ListDistrict>, response: Response<ListDistrict>) {
                val district = response.body()
                val list = district?.listDistrict
                if(list!=null){
                    for (i in 0 until list.size){
                        if(list[i].CityCode==City.CityCode){
                            val dtr = DistrictData()
                            dtr.CityCode = list[i].CityCode
                            dtr.DistrictCode = list[i].DistrictCode
                            dtr.DistrictName = list[i].DistrictName
                            listDistrict.add(dtr)
                        }
                    }
                    rv_district.adapter = DistrictAdapter(listDistrict, c as DistrictActivity)
                }
            }
            override fun onFailure(call: Call<ListDistrict>, t: Throwable) {
            }
        })
    }
}
