package com.example.sapomobile.screen.adapter

import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.District
import com.example.sapomobile.model.DistrictData

class DistrictAdapter(list: ArrayList<DistrictData>, onClickItemListener: OnClickItemListener) : AdapterBase<DistrictData>(list,onClickItemListener) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindData(getName(list),getCode(list),District.DistrictCode)
    }
    private fun getName(listDistrict: ArrayList<DistrictData>) : ArrayList<String>{
        val listName : ArrayList<String> = ArrayList()
        for (i in 0 until listDistrict.size){
            val name  = listDistrict[i].DistrictName
            listName.add(name)
        }
        return listName
    }
    private fun getCode(listDistrict: ArrayList<DistrictData>) : ArrayList<Int>{
        val listCode : ArrayList<Int> = ArrayList()
        for (i in 0 until listDistrict.size){
            val name  = listDistrict[i].DistrictCode
            listCode.add(name)
        }
        return listCode
    }
}