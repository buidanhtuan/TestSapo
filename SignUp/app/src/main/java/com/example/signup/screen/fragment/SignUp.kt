package com.example.signup.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.signup.MainActivity
import com.example.signup.R
import com.example.signup.database.DatabaseHelper
import com.example.signup.model.Account
import com.example.signup.model.User
import com.example.signup.utils.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignUp : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        view.button.setOnClickListener {
            if(setText(getInput())){
                val user = getInput()
                val account = Account()
                if (user.name != "")  account.name = user.name
                else account.name = DatabaseHelper.getAllData().size.toString()
                account.email = user.email
                account.password = user.password
                MainActivity.userName = account.name
                DatabaseHelper.insertData(account)
                (activity as MainActivity).setFragment(CityFragment())
            }
        }
        return view
    }
    private fun getInput() : User {
        return User(
            et_main_user_name.text.toString(),
            et_main_email.text.toString(),
            et_main_password.text.toString(),
            et_main_repeat_password.text.toString())
    }
    private fun setText(user: User) : Boolean{
        tv_main_report_user_name.text = user.name.checkUserName()
        tv_main_report_email.text = user.email.checkEmail()
        tv_main_report_password.text = user.password.checkPassword()
        tv_main_report_repeat_password.text = user.repeat.checkRepeatPassword(user.password)
        return check(user.name,user.email,user.password,user.repeat)
    }
}