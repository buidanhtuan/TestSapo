package com.buidanh.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var number1:Int = input1.text.toString().toInt()
            var number2:Int = input2.text.toString().toInt()
            var result:Int = number1 + number2
            output.text = result.toString()
        }
    }
}
