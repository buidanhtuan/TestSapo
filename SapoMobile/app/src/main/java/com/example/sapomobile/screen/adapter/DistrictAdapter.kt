package com.example.sapomobile.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.District
import com.example.sapomobile.model.DistrictData
import kotlinx.android.synthetic.main.adapter_district.view.*

class DistrictAdapter(val listDistrict: ArrayList<DistrictData>, private val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<DistrictAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_district, parent, false))
    }

    override fun getItemCount(): Int {
        return listDistrict.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.tv_district_adapter.text = listDistrict[adapterPosition].DistrictName
            itemView.setOnClickListener(this)
//            if(listDistrict[adapterPosition].DistrictCode==District.DistrictCode){
//              //  itemView.setBackgroundResource(R.drawable.shape_button)
//            }
//           // else itemView.setBackgroundResource(R.drawable.shape_recyclerview)
        }

        override fun onClick(v: View?) {
            onClickItemListener.onClickItem(adapterPosition)
        }
    }
}