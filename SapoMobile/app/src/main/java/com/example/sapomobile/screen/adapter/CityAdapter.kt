package com.example.sapomobile.screen.adapter

import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.City
import com.example.sapomobile.model.CityData

class CityAdapte(list: ArrayList<CityData>,onClickItemListener: OnClickItemListener) : AdapterBase<CityData>(list,onClickItemListener) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindData(getName(list),getCode(list),City.CityCode)
    }
    private fun getName(listCity: ArrayList<CityData>) : ArrayList<String>{
        val listName : ArrayList<String> = ArrayList()
        for (i in 0 until listCity.size){
            val name  = listCity[i].CityName
            listName.add(name)
        }
        return listName
    }
    private fun getCode(listCity: ArrayList<CityData>) : ArrayList<Int>{
        val listCode : ArrayList<Int> = ArrayList()
        for (i in 0 until listCity.size){
            val name  = listCity[i].CityCode
            listCode.add(name)
        }
        return listCode
    }
}