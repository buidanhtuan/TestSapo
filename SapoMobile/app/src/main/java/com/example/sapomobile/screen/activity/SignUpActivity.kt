package com.example.sapomobile.screen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sapomobile.R
import com.example.sapomobile.model.User
import com.example.sapomobile.utils.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        button.setOnClickListener{
            User.userName = et_main_user_name.text.toString()
            User.email = et_main_email.text.toString()
            User.password = et_main_password.text.toString()
            User.repeatPassword = et_main_repeat_password.text.toString()
            tv_main_report_user_name.text = User.userName.checkUserName()
            tv_main_report_email.text = User.email.checkEmail()
            tv_main_report_password.text = User.password.checkPassword()
            tv_main_report_repeat_password.text = User.repeatPassword.checkRepeatPassword(User.password)
            val check: Boolean =check(User.userName, User.email, User.password, User.repeatPassword)
            if(!check){
                val intent = Intent (this, CityActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
