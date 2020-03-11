package com.example.sapomobile.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sapomobile.R
import com.example.sapomobile.interfaces.OnClickItemListener
import com.example.sapomobile.model.CityData
import com.example.sapomobile.screen.activity.CityActivity
import kotlinx.android.synthetic.main.adapter_city.view.*

class CityAdapter(private val listCity: ArrayList<CityData>, private val onClickItemListener: CityActivity) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_city, parent, false),onClickItemListener)
    }

    override fun getItemCount(): Int {
        return listCity.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listCity)
    }
    class ViewHolder(itemView: View, private val onClickItemListener: OnClickItemListener) : RecyclerView.ViewHolder(itemView),

        View.OnClickListener {
        fun bindData(listCity: ArrayList<CityData>) {
            itemView.tv_city_adapter.text = listCity[adapterPosition].CityName
            itemView.setOnClickListener(this)
//            if(listCity[adapterPosition].CityCode==City.CityCode){
//                //itemView.setBackgroundResource(R.drawable.shape_button)
//            }
//            //else itemView.setBackgroundResource(R.drawable.shape_recyclerview)
        }
        override fun onClick(v: View?) {
            onClickItemListener.onClickItem(adapterPosition)
        }
    }
}