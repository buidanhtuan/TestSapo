package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.buidanhtuan.sapo_mobile.R
import kotlinx.android.synthetic.main.activity_age.*
import kotlin.math.absoluteValue

class AgeActivity : AppCompatActivity() {
    companion object {
        var age = 0
        var sex = "Nam"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)
        val radioGroup = findViewById<RadioGroup>(R.id.rg_age)
        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            if(R.id.radioButton==checkedId) sex = "Nam"
            if(R.id.radioButton2==checkedId) sex = "Nữ"
            if(R.id.radioButton3 ==checkedId) sex = "Khác"
        }
        bt_age.setOnClickListener {
            age = np_age.value
            val intent: Intent = Intent (this, SummaryActivity::class.java)
            startActivity(intent)
        }
    }
}
