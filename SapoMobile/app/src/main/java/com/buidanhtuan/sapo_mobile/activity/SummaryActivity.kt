package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.buidanhtuan.sapo_mobile.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        et_summary_user_name.setText(MainActivity.userName)
        et_summary_email.setText(MainActivity.email)
        et_summary_age.setText(AgeActivity.age.toString())
        et_summary_sex.setText(AgeActivity.sex)
        et_summary_city.setText(CityActivity.cityName)
        et_summery_district.setText(DistrictActivity.districtName)
        bt_summary.setOnClickListener {
            val intent: Intent = Intent (this, WellcomeActivity::class.java)
            startActivity(intent)
        }
    }
}
