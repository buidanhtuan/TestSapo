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
import com.example.signup.model.District
import com.example.signup.model.ListDistrict
import com.example.signup.screen.adapter.DistrictAdapter
import com.example.signup.utils.saveDistrictPosition
import kotlinx.android.synthetic.main.fragment_district.*

class DistrictFragment : Fragment(), OnClickItemListener {
    private val apiClient = ApiClient().getClient()?.create(ApiInterface::class.java)
    private lateinit var adapter: DistrictAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_district, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun onClickItem(position: Int) {
        val district= adapter.getItemPosition(position)
        val account : Account = Account()
        account.district = district.name
        account.name = MainActivity.userName
        DatabaseHelper.update(account,"district")
        activity?.let { saveDistrictPosition(position, it) }
        (activity as MainActivity).setFragment(AgeFragment())
    }
    private fun initView() {
        rv_district.layoutManager = LinearLayoutManager(activity as MainActivity)
        adapter = DistrictAdapter(this)
        rv_district.adapter = adapter
        getDistrict()
    }

    private fun getDistrict() {
        val info = activity?.getSharedPreferences("infoUser", Context.MODE_PRIVATE)
        apiClient?.getDistrict()?.enqueue(object : retrofit2.Callback<ListDistrict> {
            override fun onResponse(
                call: retrofit2.Call<ListDistrict>,
                response: retrofit2.Response<ListDistrict>
            ) {
                val districts = response.body()?.listDistrict
                val dtr : ArrayList<District> = ArrayList()
                if (districts != null) {
                    for(i in districts.indices){
                        if (districts[i].cityCode == info!!.getInt("cityCode",0)){
                            val d = District(
                                districts[i].cityCode,
                                districts[i].name,
                                districts[i].code
                            )
                            dtr.add(d)
                        }
                    }
                }
                dtr.let {
                    adapter.updateList(it)
                }
                rv_district.scrollToPosition(info!!.getInt("districtPosition",0))
            }
            override fun onFailure(call: retrofit2.Call<ListDistrict>, t: Throwable) {}
        })
    }
}