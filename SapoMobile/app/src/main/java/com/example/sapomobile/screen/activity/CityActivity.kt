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
import com.example.sapomobile.model.ListCity
import com.example.sapomobile.screen.adapter.CityAdapter
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity : AppCompatActivity(), OnClickItemListener {
    private val apiClient = ApiClient().getClient()?.create(ApiInterface::class.java)
    private lateinit var adapter: CityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        initView()
    }

    override fun onClickItem(position: Int) {
        val intent = Intent(this, DistrictActivity::class.java)
        val city=adapter.getItemPosition(position)
        City.CityName =city.CityName
        City.CityCode =city.CityCode
        //TODO Xem lại đoạn lưu lại vị trí nha. Không ai reload lại layout trước khi next màn đâu
        getCity(this, position)
        startActivity(intent)
    }

    /**
     * init view
     */
    private fun initView() {
        rv_city.layoutManager = LinearLayoutManager(this)
        adapter = CityAdapter(this)
        rv_city.adapter = adapter
        getCity(this, 0)

    }

    private fun getCity(c: Context, position: Int) {
        apiClient?.getCity()?.enqueue(object : retrofit2.Callback<ListCity> {
            override fun onResponse(
                call: retrofit2.Call<ListCity>,
                response: retrofit2.Response<ListCity>
            ) {
                val cities = response.body()?.listCity
                cities?.let {
                    adapter.updateList(it)
                    rv_city.scrollToPosition(position)
                }
            }

            override fun onFailure(call: retrofit2.Call<ListCity>, t: Throwable) {}
        })
    }
}

