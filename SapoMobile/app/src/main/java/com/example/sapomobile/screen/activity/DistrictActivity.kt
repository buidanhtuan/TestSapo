package com.example.sapomobile.screen.activity

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
    private lateinit var adapter: DistrictAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district)
        initView()

    }

    override fun onClickItem(position: Int) {
        val district = adapter.getItemPosition(position)
        District.DistrictName = district.DistrictName
        District.DistrictCode = district.DistrictCode
        val intent = Intent(this, AgeActivity::class.java)

        //TODO Xem lại đoạn lưu lại vị trí nha. Không ai reload lại layout trước khi next màn đâu
        getDistrict(position)
        startActivity(intent)
    }

    /**
     * init view
     */
    private fun initView() {
        rv_district.layoutManager = LinearLayoutManager(this)
        adapter = DistrictAdapter(this@DistrictActivity)
        rv_district.adapter = adapter
        getDistrict(0)
    }

    private fun getDistrict(position: Int) {
        apiClient?.getDistrict()?.enqueue(object : Callback<ListDistrict> {
            override fun onResponse(call: Call<ListDistrict>, response: Response<ListDistrict>) {
                val districts = response.body()?.listDistrict
                districts?.let { ls ->
                    ls.groupBy { it.CityCode }[City.CityCode]
                    rv_district.scrollToPosition(position)
                }
            }

            override fun onFailure(call: Call<ListDistrict>, t: Throwable) {
            }
        })
    }
}
