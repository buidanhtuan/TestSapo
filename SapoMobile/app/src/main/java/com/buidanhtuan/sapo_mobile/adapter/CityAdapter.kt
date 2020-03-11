package com.buidanhtuan.sapo_mobile.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.sapo_mobile.Interface.OnClickItemListener
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.activity.CityActivity
import com.buidanhtuan.sapo_mobile.activity.MainActivity
import kotlinx.android.synthetic.main.adapter_city.view.*

class CityAdapter (val listCity: ArrayList<CityActivity.City>, val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_city, parent, false),onClickItemListener)
    }

    override fun getItemCount(): Int {
        return listCity.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData()
    }

    inner class ViewHolder(itemView: View, val onClickItemListener: OnClickItemListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.tv_city_adapter.text = listCity[adapterPosition].cityName
            itemView.setOnClickListener(this)
            if(listCity[adapterPosition].cityCode==CityActivity.cityCode){
                itemView.setBackgroundResource(R.drawable.shape_button)
            }
            else itemView.setBackgroundResource(R.drawable.shape_recyclerview)
        }
        override fun onClick(v: View?) {
            onClickItemListener.onClickItem(adapterPosition)
        }
    }
}