package com.example.sapomobile.utils

import java.util.regex.Pattern

/**
 * Nếu tất cả function trong clas mà nằm trong  companion object block
 * thì em có thể chuyển class đó dạng object nó sẽ tự ngầm hiểu các
 * function trong là dạng static
 */
//class CheckInput {
object CheckInput {
    //    companion object {
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

    fun check(userName: String, emai: String, password: String, repeatPasword: String): Boolean {
        val c1 = checkUserName(userName)
        val c2 = checkEmail(emai)
        // TODO Khi dùng nó đơn giản vậy thui và có thể dùng ở mọi nơi như 1 function dk tạo ra
        // cho String
//            val c3 = checkPassword(password)
        val c3 = password.checkPassword()
        val c4 = checkRepeatPassword(password, repeatPasword)
        return c1 + c2 + c3 + c4 == ""
    }

//    }
}


/**
 * Tìm hiểu các viết này trong kotlin nha
 * Cái này khá hay VD
 * Nó ứng dụng khi function này cần xử lý trong nhiều nơi nhiều màn hình
 * Thì nên suy nghĩ dùng cái này
 * Còn ở đây chỉ có 1 màn có thể không cần thiết
 * Lưu ý nó chỉ nằm trong file khong nằm trong class
 * Thử viết 1 function để laucher 1 activity dạng này xem nha
 * em sẽ thấy cái hay của nó
 * đó là vd trực quan nhất
 */

fun String.checkPassword(): String {
    val exp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.,=+_`~])(?=\\S+$).{9,16}$"
    val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return if (this.isEmpty()) "Hãy nhập mật khẩu"
    else if (!matcher.matches()) "Mật khẩu chưa đúng định dạng"
    else ""
}