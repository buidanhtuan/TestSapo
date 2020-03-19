package com.example.signup.screen.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.signup.MainActivity
import com.example.signup.R
import com.example.signup.database.DatabaseHelper
import com.example.signup.interfaces.OnClickItemListener
import com.example.signup.model.City
import com.example.signup.utils.Diffcallback
import kotlinx.android.synthetic.main.adapter.view.*

class CityAdapter(onClickItemListener: OnClickItemListener) : AdapterBase<City>(onClickItemListener){

    override fun bindData(holder: RecyclerView.ViewHolder) {
        holder.itemView.tv_adapter.text = list[holder.adapterPosition].name
        if(list[holder.adapterPosition].name==DatabaseHelper.getData(MainActivity.userName).city){
            holder.itemView.setBackgroundResource(R.drawable.shape_select)
        }
        else holder.itemView.setBackgroundResource(R.drawable.shape_non_select)
    }
    override fun updateList(newData: List<City>) {
        val diffcallback = Diffcallback(list,newData)
        val diffResult = DiffUtil.calculateDiff(diffcallback)
        list = newData
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemLayout(): Int {
        return R.layout.adapter
    }
}