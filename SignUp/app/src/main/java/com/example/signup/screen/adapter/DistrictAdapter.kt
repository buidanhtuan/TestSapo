package com.example.signup.screen.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.signup.R
import com.example.signup.interfaces.OnClickItemListener
import com.example.signup.model.City
import com.example.signup.model.District
import com.example.signup.utils.Diffcallback
import kotlinx.android.synthetic.main.adapter.view.*

class DistrictAdapter(onClickItemListener: OnClickItemListener) : AdapterBase<District>(onClickItemListener) {

    override fun bindData(holder: RecyclerView.ViewHolder) {
        holder.itemView.tv_adapter.text = list[holder.adapterPosition].name
    }

    override fun updateList(newData: List<District>) {
        val diffcallback = Diffcallback<District>(list,newData)
        val diffResult = DiffUtil.calculateDiff(diffcallback)
        list = newData
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemLayout(): Int {
        return R.layout.adapter
    }
}