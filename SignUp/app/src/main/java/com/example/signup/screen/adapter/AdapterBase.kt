package com.example.signup.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.signup.interfaces.OnClickItemListener

abstract class AdapterBase<T>(
    val onClickItemListener: OnClickItemListener
) :
    RecyclerView.Adapter<AdapterBase<T>.ViewHolder>() {

    var list: List<T> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(getItemLayout(), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindData(holder)
        holder.bindData()
    }

    fun getItemPosition(position: Int): T = list[position]

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickItemListener.onClickItem(adapterPosition)
        }
    }

    abstract fun updateList(newData: List<T>)

    abstract fun getItemLayout(): Int

    abstract fun bindData(holder: RecyclerView.ViewHolder)
}
