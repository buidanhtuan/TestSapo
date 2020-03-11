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

/**
 * 2 adapter City vs Distric khá jog nhau e xem tạo 1 class adapter base cho c nha
 */
class CityAdapter(private val listCity: ArrayList<CityData>, private val onClickItemListener: CityActivity) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_city, parent, false))
    }

    override fun getItemCount(): Int {
        return listCity.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listCity)
    }

    /**
     * FIXME Nếu đã để trong class cha thì nên dùng inner
     * khi dùng inner thì mọi thuộc tính của thằng cha e đều dùng như 1 dạng biến local dk nha
     * vậy nên khi chuyển về inner class thì private val onClickItemListener: OnClickItemListener
     * có thể dùng luôn thằng cha k phần truyền param nha
     *
     */
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),

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