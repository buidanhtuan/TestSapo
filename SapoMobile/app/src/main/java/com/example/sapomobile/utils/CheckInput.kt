package com.example.sapomobile.utils

import java.util.regex.Pattern

    fun check(userName: String, email: String, password: String, repeatPasword: String): Boolean {
        val c1 = userName.checkUserName()
        val c2 = email.checkEmail()
        val c3 = password.checkPassword()
        val c4 = repeatPasword.checkRepeatPassword(password)
        return c1 + c2 + c3 + c4 == ""
    }
    fun String.checkPassword(): String {
        val exp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.,=+_`~])(?=\\S+$).{9,16}$"
        val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(this)
        return if (this.isEmpty()) "Hãy nhập mật khẩu"
        else if (!matcher.matches()) "Mật khẩu chưa đúng định dạng"
        else ""
    }
    fun String.checkUserName(): String {
        return when {
            this.length > 8 -> ""
            this.isEmpty() -> "Hãy nhập UserName"
            else -> "User name cần > 8 kí tự"
        }
    }
    fun String.checkEmail(): String {
        val length = this.length
        return when {
            length == 0 -> "Hãy nhập Email"
            length <= 10 -> "Email chưa đúng định dạng"
            this.subSequence(length - 10, length) != "@gmail.com" -> "Email chưa đúng định dạng"
            else -> ""
        }
    }
    fun String.checkRepeatPassword(password: String): String {
        return when {
            this == password -> ""
            this.isEmpty() -> "Hãy nhập lại mật khẩu"
            else -> "Chưa đúng mật khẩu"
        }
    }