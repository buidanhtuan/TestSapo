package com.buidanhtuan.test4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class Item ( val name: String )

class RecyclerView : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var adapter: RecyclerViewAdapter
    val list: List<Item> = listOf(
        Item( "item0")
        ,Item( "item1")
        ,Item( "item2")
        ,Item( "item3")
        ,Item( "item4")
        ,Item( "item5")
        ,Item( "item6"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        recyclerView = findViewById(R.id.recycle_view)
        adapter = RecyclerViewAdapter(list, this)
        recyclerView.adapter = adapter
    }
}
