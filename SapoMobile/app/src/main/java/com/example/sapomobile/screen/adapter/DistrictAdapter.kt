package com.example.sapomobile.screen.adapter

import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.District
import com.example.sapomobile.model.DistrictData
import kotlinx.android.synthetic.main.adapter.view.*

class DistrictAdapter(list: ArrayList<DistrictData>, onClickItemListener: OnClickItemListener) : AdapterBase<DistrictData>(list,onClickItemListener) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.tv_adapter.text = list[position].DistrictName
        holder.bindData()
        if(list[position].DistrictCode== District.DistrictCode){
            holder.itemView.setBackgroundResource(R.drawable.shape_select)
        }
        else holder.itemView.setBackgroundResource(R.drawable.shape_non_select)
    }
}