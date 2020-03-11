package com.example.sapomobile.screen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sapomobile.R
import com.example.sapomobile.model.City
import com.example.sapomobile.model.District
import com.example.sapomobile.model.User
import kotlinx.android.synthetic.main.activity_summary.*

/**
 * tại sao là open class
 */
open class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        et_summary_user_name.setText(User.userName)
        et_summary_email.setText(User.email)
        et_summary_age.setText(User.age.toString())
        et_summary_sex.setText(User.sex)
        et_summary_city.setText(City.CityName)
        et_summery_district.setText(District.DistrictName)
        bt_summary.setOnClickListener {
            val intent = Intent (this, WellcomeActivity::class.java)
            startActivity(intent)
        }
    }
}
