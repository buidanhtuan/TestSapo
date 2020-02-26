package com.buidanh.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var intent = getIntent()
        var a:Int = intent.getIntExtra("Number1",0)
        var b:Int = intent.getIntExtra("Number2", 0)
        var sum = a + b
        textView.setText(sum.toString());
        button2.setOnClickListener {
            finish()
        }
    }
}
