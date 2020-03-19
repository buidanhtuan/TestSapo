package com.example.signup.screen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.signup.R
import com.example.signup.interfaces.OnClickItemListener
import com.example.signup.model.City
import kotlinx.android.synthetic.main.adapter.view.*

class CityAdapter(onClickItemListener: OnClickItemListener) : AdapterBase<City>(onClickItemListener){

    override fun bindData(holder: RecyclerView.ViewHolder) {
        holder.itemView.tv_adapter.text = list[holder.adapterPosition].name
//        if(list[holder.adapterPosition].CityCode==City.CityCode){
//            holder.itemView.setBackgroundResource(R.drawable.shape_select)
//        }
//        else holder.itemView.setBackgroundResource(R.drawable.shape_non_select)
    }
    override fun updateList(newData: List<City>) {
        list = newData
        // TODO tim hieu diffUtil.Callback va thay the cai nay cho c
        notifyDataSetChanged()
    }

    override fun getItemLayout(): Int {
        return R.layout.adapter
    }
}