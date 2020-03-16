package com.example.sapomobile.screen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.City
import com.example.sapomobile.model.CityData
import kotlinx.android.synthetic.main.adapter.view.*

class CityAdapter(onClickItemListener: OnClickItemListener) : AdapterBase<CityData>(onClickItemListener){

    override fun bindData(holder: RecyclerView.ViewHolder) {
        holder.itemView.tv_adapter.text = list[holder.adapterPosition].CityName
        if(list[holder.adapterPosition].CityCode==City.CityCode){
            holder.itemView.setBackgroundResource(R.drawable.shape_select)
        }
        else holder.itemView.setBackgroundResource(R.drawable.shape_non_select)
    }
    override fun updateList(newData: List<CityData>) {
        list = newData
        // TODO tim hieu diffUtil.Callback va thay the cai nay cho c
        notifyDataSetChanged()
    }

    override fun getItemLayout(): Int {
        return R.layout.adapter
    }
}