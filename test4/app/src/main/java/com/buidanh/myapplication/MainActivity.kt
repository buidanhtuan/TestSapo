package com.buidanh.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class Item ( val name: String )

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var adapter: RecyclerViewAdapter
    val list: List<Item> = listOf(
        Item( "item0")
        ,Item( "item1")
        ,Item( "item2")
        ,Item( "item3")
        ,Item( "item4")
        ,Item( "item5")
        ,Item( "item6")
        ,Item( "item7")
        ,Item( "item8")
        ,Item( "item9"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycle_view)
        adapter = RecyclerViewAdapter(list, this)
        recyclerView.adapter = adapter
    }
}
