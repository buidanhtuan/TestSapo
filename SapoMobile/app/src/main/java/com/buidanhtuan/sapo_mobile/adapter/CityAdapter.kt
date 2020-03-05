package com.buidanhtuan.sapo_mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.sapo_mobile.R
import kotlinx.android.synthetic.main.adapter_city.view.*

class CityAdapter (val listName: ArrayList<String>, val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_city, parent, false),onClickItemListener)
    }

    override fun getItemCount(): Int {
        return listName.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData()
    }

    inner class ViewHolder(itemView: View, val onClickItemListener: OnClickItemListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.tv_city_adapter.text = listName[adapterPosition]
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickItemListener.onClickItem(adapterPosition)
        }
    }

    interface OnClickItemListener{
        fun onClickItem(position : Int){
        }
    }
}