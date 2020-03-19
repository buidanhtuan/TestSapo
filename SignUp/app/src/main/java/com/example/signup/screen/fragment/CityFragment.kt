package com.example.signup.screen.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.signup.MainActivity
import com.example.signup.R
import com.example.signup.api.ApiClient
import com.example.signup.api.ApiInterface
import com.example.signup.database.DatabaseHelper
import com.example.signup.interfaces.OnClickItemListener
import com.example.signup.model.Account
import com.example.signup.model.ListCity
import com.example.signup.screen.adapter.CityAdapter
import com.example.signup.utils.saveCityCode
import com.example.signup.utils.saveCityPosition
import kotlinx.android.synthetic.main.fragment_city.*

class CityFragment : Fragment(), OnClickItemListener {
    private val apiClient = ApiClient().getClient()?.create(ApiInterface::class.java)
    private lateinit var adapter: CityAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun onClickItem(position: Int) {
        val account = Account()
        account.city = adapter.getItemPosition(position).name
        account.name = MainActivity.userName
        DatabaseHelper.update(account,"city")
        activity?.let { saveCityPosition(position, it) }
        activity?.let { saveCityCode(adapter.getItemPosition(position).code,it) }
        (activity as MainActivity).setFragment(DistrictFragment())
    }
    private fun initView() {
        rv_city.layoutManager = LinearLayoutManager(activity as MainActivity)
        adapter = CityAdapter(this)
        rv_city.adapter = adapter
        getCity()
    }
    private fun getCity() {
        val info = activity?.getSharedPreferences("infoUser", Context.MODE_PRIVATE)
        apiClient?.getCity()?.enqueue(object : retrofit2.Callback<ListCity> {
            override fun onResponse(
                call: retrofit2.Call<ListCity>,
                response: retrofit2.Response<ListCity>
            ) {
                val cities = response.body()?.listCity
                cities?.let {
                    adapter.updateList(it)
                }
                (rv_city.layoutManager as LinearLayoutManager).scrollToPosition(info!!.getInt("cityPosition",0))
            }
            override fun onFailure(call: retrofit2.Call<ListCity>, t: Throwable) {}
        })
    }
}