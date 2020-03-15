package com.example.sapomobile.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener

abstract class AdapterBase<T>(val list: ArrayList<T>, val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<AdapterBase<T>.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData(){
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onClickItemListener.onClickItem(adapterPosition)
        }
    }
    abstract fun updateList(newData:ArrayList<T>)
    abstract fun getItemLayout(holder: ViewHolder):Int
}