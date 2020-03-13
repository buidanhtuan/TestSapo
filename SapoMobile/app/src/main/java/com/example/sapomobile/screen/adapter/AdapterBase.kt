package com.example.sapomobile.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener
import kotlinx.android.synthetic.main.adapter.view.*

abstract class AdapterBase<T>(val list: ArrayList<T>, private val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<AdapterBase<T>.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter, parent, false))    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData(listName: ArrayList<String>,listCode: ArrayList<Int>,code: Int){
            itemView.tv_adapter.text = listName[adapterPosition]
            itemView.setOnClickListener(this)
            if(listCode[adapterPosition]==code){
                itemView.setBackgroundResource(R.drawable.shape_select)
            }
            else itemView.setBackgroundResource(R.drawable.shape_non_select)
        }
        override fun onClick(v: View?) {
            onClickItemListener.onClickItem(adapterPosition)
        }
    }
}