package com.example.signup.screen.fragment

import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_city.*
import org.koin.core.qualifier.named

class CityFragment : Fragment(), OnClickItemListener {
    companion object{
        var code = 0;
    }
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
        val city=adapter.getItemPosition(position)
        code = city.code
        var account : Account = Account()
        account.city = city.name
        account.name = MainActivity.userName
        DatabaseHelper.update(account,"city")
        for (i in 0 until DatabaseHelper.getAllData().size){
            System.out.println(DatabaseHelper.getAllData()[i])
        }
        (activity as MainActivity).setFragment(DistrictFragment())
    }
    private fun initView() {
        rv_city.layoutManager = LinearLayoutManager(activity as MainActivity)
        adapter = CityAdapter(this)
        rv_city.adapter = adapter
        getCity()
    }

    private fun getCity() {
        apiClient?.getCity()?.enqueue(object : retrofit2.Callback<ListCity> {
            override fun onResponse(
                call: retrofit2.Call<ListCity>,
                response: retrofit2.Response<ListCity>
            ) {
                val cities = response.body()?.listCity
                cities?.let {
                    adapter.updateList(it)
                }
            }
            override fun onFailure(call: retrofit2.Call<ListCity>, t: Throwable) {}
        })
    }
}