package com.buidanhtuan.sapo_mobile.input

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

@SuppressLint("Registered")
class CheckInputMain : AppCompatActivity(){
    @SuppressLint("SetTextI18n")
    private fun checkUserName (userName : String, tv_main_report_user_name : TextView) : Boolean{
        return when {
            userName.length>8 -> true
            userName.isEmpty() -> {
                tv_main_report_user_name.text="Hãy nhập UserName"
                false
            }
            else -> {
                tv_main_report_user_name.text="User name cần > 8 kí tự"
                false
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun checkEmail (emai : String, tv_main_report_email : TextView) : Boolean{
        val length = emai.length
        return when {
            length==0 -> {
                tv_main_report_email.text="Hãy nhập Email"
                false
            }
            length<=10 -> {
                tv_main_report_email.text = "Email chưa đúng định dạng"
                false
            }
            emai.subSequence(length-10,length) != "@gmail.com" -> {
                tv_main_report_email.text = "Email chưa đúng định dạng"
                false
            }
            else -> true
        }
    }
    @SuppressLint("SetTextI18n")
    private fun checkPassword (password : String, tv_main_report_password : TextView) : Boolean{
        val exp =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.,=+_`~])(?=\\S+$).{9,16}$"
        val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(password)
        if (password.isEmpty()) tv_main_report_password.text="Hãy nhập mật khẩu"
        else if(!matcher.matches()) tv_main_report_password.text="Mật khẩu chưa đúng định dạng"
        else return true
        return false
    }
    @SuppressLint("SetTextI18n")
    private fun checkRepeatPassword (password: String, RepeatPasword : String, tv_main_report_repeat_password: TextView) : Boolean{
        return when {
            RepeatPasword==password -> true
            RepeatPasword.isEmpty() -> {
                tv_main_report_repeat_password.text = "Hãy nhập lại mật khẩu"
                false
            }
            else -> {
                tv_main_report_repeat_password.text = "Chưa đúng mật khẩu"
                false
            }
        }
    }
    fun check(userName: String,emai: String,password: String,RepeatPasword: String,
    tv_main_report_user_name:  TextView, tv_main_report_email: TextView,tv_main_report_password: TextView,tv_main_report_repeat_password: TextView
    ):Boolean{
        checkUserName(userName,tv_main_report_user_name)
        checkEmail(emai,tv_main_report_email)
        checkPassword(password,tv_main_report_password)
        checkRepeatPassword(password,RepeatPasword,tv_main_report_repeat_password)
        val c1 = checkUserName(userName,tv_main_report_user_name)
        val c2 = checkEmail(emai,tv_main_report_email)
        val c3 = checkPassword(password,tv_main_report_password)
        val c4 = checkRepeatPassword(password,RepeatPasword,tv_main_report_repeat_password)
        return c1 && c2 && c3 && c4
    }
}