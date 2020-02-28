package com.buidanhtuan.test4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler_view_item_detail.*

class RecyclerViewItemDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_item_detail)
        var name: String = intent.getStringExtra("Name")
        text.setText(name)
    }
}
