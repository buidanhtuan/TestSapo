package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buidanhtuan.sapo_mobile.R
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
            var checkall = check(userName, email, password, repeatPassword)
            if(checkall) {
                val intent: Intent = Intent (this, CityActivity::class.java)
                startActivity(intent)
            }
        }
    }
    fun checkUserName (userName : String) : Boolean{
        if(userName.length>8) return true
        else if(userName.length==0){
            tv_main_report_user_name.text="Hãy nhập UserName"
            return false
        }
        else{
            tv_main_report_user_name.text="User name cần > 8 kí tự"
            return false
        }
    }
    fun checkEmail (emai : String) : Boolean{
        var length = emai.length
        if(length==0){
            tv_main_report_email.text="Hãy nhập Email"
            return false
        }
        else if(length<=10){
            tv_main_report_email.text = "Email chưa đúng định dạng"
            return false
        }
        else if(!(emai.subSequence(length-10,length)=="@gmail.com")){
            tv_main_report_email.text = "Email chưa đúng định dạng"
            return false
        }
        else return true
    }
    fun checkPassword (password : String) : Boolean{
        val exp : String =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.,=+_`~])(?=\\S+$).{9,16}$"
        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(password)
        if (password.length==0) tv_main_report_password.text="Hãy nhập mật khẩu"
        else if(!matcher.matches()) tv_main_report_password.text="Mật khẩu chưa đúng định dạng"
        else return true
        return false
    }
    fun checkRepeatPassword (password: String,RepeatPasword : String) : Boolean{
        if(RepeatPasword==password) return true
        else if (RepeatPasword.length==0){
            tv_main_report_repeat_password.text = "Hãy nhập lại mật khẩu"
            return false
        }
        else{
            tv_main_report_repeat_password.text = "Chưa đúng mật khẩu"
            return false
        }
    }
    fun check(userName: String,emai: String,password: String,RepeatPasword: String):Boolean{
        checkUserName(userName)
        checkEmail(emai)
        checkPassword(password)
        checkRepeatPassword(password,RepeatPasword)
        return (checkUserName(userName)&&checkEmail(emai)&&checkPassword(password)&&checkRepeatPassword(password,RepeatPasword))
    }
}
