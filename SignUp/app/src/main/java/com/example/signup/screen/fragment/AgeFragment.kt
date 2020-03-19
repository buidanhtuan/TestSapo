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
import kotlinx.android.synthetic.main.fragment_age.view.*

class AgeFragment :  Fragment(), OnClickItemListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_age, container, false)
        var account : Account = Account()
        view.rg_age.setOnCheckedChangeListener { _, checkedId ->
            if(R.id.radioButton==checkedId)   account.sex = "Nam"
            if(R.id.radioButton==checkedId)   account.sex = "Nam"
            if(R.id.radioButton2==checkedId)  account.sex = "Nữ"
            if(R.id.radioButton3 ==checkedId) account.sex = "Khác"
        }
        view.bt_age.setOnClickListener {
            account.age = view.np_age.value
            account.name = MainActivity.userName
            DatabaseHelper.update(account,"sex,age")
            (activity as MainActivity).setFragment(SummaryFragment())
        }
        return view
    }
}