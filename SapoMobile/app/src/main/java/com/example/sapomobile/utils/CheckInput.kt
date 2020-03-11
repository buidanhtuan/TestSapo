package com.example.sapomobile.utils

import java.util.regex.Pattern

class CheckInput {
    companion object {
        fun checkUserName(userName: String): String {
            return when {
                userName.length > 8 -> ""
                userName.isEmpty() -> "Hãy nhập UserName"
                else -> "User name cần > 8 kí tự"
            }
        }

        fun checkEmail(emai: String): String {
            val length = emai.length
            return when {
                length == 0 -> "Hãy nhập Email"
                length <= 10 -> "Email chưa đúng định dạng"
                emai.subSequence(length - 10, length) != "@gmail.com" -> "Email chưa đúng định dạng"
                else -> ""
            }
        }

        fun checkPassword(password: String): String {
            val exp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.,=+_`~])(?=\\S+$).{9,16}$"
            val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(password)
            return if (password.isEmpty()) "Hãy nhập mật khẩu"
            else if (!matcher.matches()) "Mật khẩu chưa đúng định dạng"
            else ""
        }

        fun checkRepeatPassword(password: String, repeatPasword: String): String {
            return when {
                repeatPasword == password -> ""
                repeatPasword.isEmpty() -> "Hãy nhập lại mật khẩu"
                else -> "Chưa đúng mật khẩu"
            }
        }

        fun check(userName: String, emai: String, password: String, repeatPasword:String) : Boolean {
            val c1 = checkUserName(userName)
            val c2 = checkEmail(emai)
            val c3 = checkPassword(password)
            val c4 = checkRepeatPassword(password,repeatPasword)
            return c1+c2+c3+c4 == ""
        }
    }
}