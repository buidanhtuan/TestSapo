package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.input.CheckInputMain
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_summary.*
import java.util.regex.Pattern


open class MainActivity : AppCompatActivity() {
    companion object {
        var userName = ""
        var email = ""
        var password = ""
        var repeatPassword = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            userName = et_main_user_name.text.toString()
            email = et_main_email.text.toString()
            password = et_main_password.text.toString()
            repeatPassword = et_main_repeat_password.text.toString()
            tv_main_report_user_name.text = ""
            tv_main_report_email.text=""
            tv_main_report_password.text=""
            tv_main_report_repeat_password.text=""
            var checkInPut = CheckInputMain()
            var check = checkInPut.check(userName, email, password, repeatPassword,
                tv_main_report_user_name,tv_main_report_email,tv_main_report_password,tv_main_report_repeat_password)
            if(check) {
                val intent: Intent = Intent (this, CityActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
