package com.buidanhtuan.sapo_mobile.input

import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

/**
 * tìm hiểu cho static trong kotlin nó dùng như thế nào  (cả function lẫn class nha)
 * rồi nếu cần thiết chuyển về các function dạng static
 */
class CheckInputMain() {
    fun checkUserName (userName : String,tv_main_report_user_name : TextView) : Boolean{
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
    fun checkEmail (emai : String, tv_main_report_email : TextView) : Boolean{
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
    fun checkPassword (password : String,tv_main_report_password : TextView) : Boolean{
        val exp : String =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.,=+_`~])(?=\\S+$).{9,16}$"
        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(password)
        if (password.length==0) tv_main_report_password.text="Hãy nhập mật khẩu"
        else if(!matcher.matches()) tv_main_report_password.text="Mật khẩu chưa đúng định dạng"
        else return true
        return false
    }
    fun checkRepeatPassword (password: String,RepeatPasword : String, tv_main_report_repeat_password: TextView) : Boolean{
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
    fun check(userName: String,emai: String,password: String,RepeatPasword: String,
    tv_main_report_user_name:  TextView, tv_main_report_email: TextView,tv_main_report_password: TextView,tv_main_report_repeat_password: TextView
    ):Boolean{
        checkUserName(userName,tv_main_report_user_name)
        checkEmail(emai,tv_main_report_email)
        checkPassword(password,tv_main_report_password)
        checkRepeatPassword(password,RepeatPasword,tv_main_report_repeat_password)
        var c1 = checkUserName(userName,tv_main_report_user_name)
        var c2 = checkEmail(emai,tv_main_report_email)
        var c3 = checkPassword(password,tv_main_report_password)
        var c4 = checkRepeatPassword(password,RepeatPasword,tv_main_report_repeat_password)
        var c  = c1 && c2 && c3 && c4
        return c
    }
}