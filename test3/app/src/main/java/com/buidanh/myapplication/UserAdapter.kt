package com.buidanh.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * User adapter to use in RecyclerView
 *
 * @author Guilherme Biff Zarelli
 */

/*
Chị đang thấy em quá làm dụng lambda trong này
Có những phần không cần dùng em lại dùng

 */
class UserAdapter(
    //I decided to create a functional interface instead of a new class.
    val onClickItem: (view: View, user: User) -> Unit// chỉ nên truyền nhưng param thực sự cần dùng
) : ListAdapter<User, UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.bind(user, createOnClickListener(user))
        }
    }

    private fun createOnClickListener(user: User): View.OnClickListener {
        return View.OnClickListener { view ->
            //Here, We call the functional interface.
            onClickItem(view, user)
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 */
/**
 * em có hiểu nó nghĩa là gì không
 */
class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.userId == newItem.userId
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.name == newItem.name
}

/**
 * Representation of the User's Item Holder
 */
class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var textViewName: TextView = view.findViewById(R.id.name)

    fun bind(user: User, onClickListener: View.OnClickListener) {
        // dùng adapterposition nha k dùng position từ onBindViewHolder sau này
        // làm các bài về thay đổi trạng thái nhiều item có thể hay bị sai đó
        textViewName.text = user.name
        itemView.setOnClickListener(onClickListener)
    }
}
