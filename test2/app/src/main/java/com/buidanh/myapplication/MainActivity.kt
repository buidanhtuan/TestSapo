package com.buidanh.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val intent: Intent = Intent (this, Main2Activity::class.java)

            var number1:Int = input1.text.toString().toInt()
            var number2:Int = input2.text.toString().toInt()

            intent.putExtra("Number1", number1)
            intent.putExtra("Number2", number2)
            startActivity(intent)
        }
    }
}
