package com.example.sapomobile.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener
import kotlinx.android.synthetic.main.adapter.view.*

class AdapterBase(private val list: ArrayList<String>,private val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<AdapterBase.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter, parent, false))    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData(listName: ArrayList<String>) {
            itemView.tv_adapter.text = listName[adapterPosition]
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onClickItemListener.onClickItem(adapterPosition)
        }
    }
}