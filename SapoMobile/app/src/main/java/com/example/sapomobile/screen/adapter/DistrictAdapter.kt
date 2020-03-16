package com.example.sapomobile.screen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.District
import com.example.sapomobile.model.DistrictData
import kotlinx.android.synthetic.main.adapter.view.*


class DistrictAdapter(
    onClickItemListener: OnClickItemListener
) :
    AdapterBase<DistrictData>(onClickItemListener) {

    override fun bindData(holder: RecyclerView.ViewHolder) {
        holder.itemView.tv_adapter.text = list[holder.adapterPosition].DistrictName
        if (list[holder.adapterPosition].DistrictCode == District.DistrictCode) {
            holder.itemView.setBackgroundResource(R.drawable.shape_select)
        } else holder.itemView.setBackgroundResource(R.drawable.shape_non_select)
    }

    override fun updateList(newData: List<DistrictData>) {
        list = newData
        // TODO tim hieu diffUtil.Callback va thay the cai nay cho c
        notifyDataSetChanged()
    }

    override fun getItemLayout(): Int {
        return R.layout.adapter
    }
}