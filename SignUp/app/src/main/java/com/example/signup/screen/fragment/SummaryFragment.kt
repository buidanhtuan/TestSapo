package com.example.signup.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.signup.MainActivity
import com.example.signup.R
import com.example.signup.database.DatabaseHelper
import com.example.signup.interfaces.OnClickItemListener
import com.example.signup.model.Account
import kotlinx.android.synthetic.main.fragment_summary.view.*

class SummaryFragment :  Fragment(), OnClickItemListener {
    private var dataDaftarMahasiswa: MutableList<Account> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        setData(view)
        view.bt_summary.setOnClickListener {
            (activity as MainActivity).setFragment(WellcomeFragment())
        }
        return view
    }
    private fun setData(view : View){
        dataDaftarMahasiswa = DatabaseHelper.getAllData()
        val account: Account = DatabaseHelper.getData(MainActivity.userName)
        view.et_summary_user_name.setText(account.name)
        view.et_summary_email.setText(account.email)
        view.et_summary_city.setText(account.city)
        view.et_summary_district.setText(account.district)
        view.et_summary_age.setText(account.age.toString())
        view.et_summary_sex.setText(account.sex)
    }
}