package com.example.signup.screen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.signup.R
import com.example.signup.interfaces.OnClickItemListener
import com.example.signup.model.District
import kotlinx.android.synthetic.main.adapter.view.*

class DistrictAdapter(onClickItemListener: OnClickItemListener) : AdapterBase<District>(onClickItemListener) {

    override fun bindData(holder: RecyclerView.ViewHolder) {
        holder.itemView.tv_adapter.text = list[holder.adapterPosition].name
    }

    override fun updateList(newData: List<District>) {
        list = newData
        // TODO tim hieu diffUtil.Callback va thay the cai nay cho c
        notifyDataSetChanged()
    }

    override fun getItemLayout(): Int {
        return R.layout.adapter
    }
}