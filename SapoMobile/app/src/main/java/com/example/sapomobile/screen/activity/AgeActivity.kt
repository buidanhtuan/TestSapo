package com.example.sapomobile.screen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sapomobile.R
import com.example.sapomobile.model.User
import kotlinx.android.synthetic.main.activity_age.*

class AgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)
        rg_age.setOnCheckedChangeListener { _, checkedId ->
            if(R.id.radioButton==checkedId)   User.sex = "Nam"
            if(R.id.radioButton==checkedId)   User.sex = "Nam"
            if(R.id.radioButton2==checkedId)  User.sex = "Nữ"
            if(R.id.radioButton3 ==checkedId) User.sex = "Khác"
        }
        bt_age.setOnClickListener {
            User.age = np_age.value
            val intent = Intent (this, SummaryActivity::class.java)
            startActivity(intent)
        }
    }
}

