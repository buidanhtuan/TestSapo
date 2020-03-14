package com.example.sapomobile.screen.adapter

import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.City
import com.example.sapomobile.model.CityData
import kotlinx.android.synthetic.main.adapter.view.*

class CityAdapte(list: ArrayList<CityData>,onClickItemListener: OnClickItemListener) : AdapterBase<CityData>(list,onClickItemListener){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.tv_adapter.text = list[position].CityName
        holder.bindData()
        if(list[position].CityCode==City.CityCode){
            holder.itemView.setBackgroundResource(R.drawable.shape_select)
        }
        else holder.itemView.setBackgroundResource(R.drawable.shape_non_select)
    }
}