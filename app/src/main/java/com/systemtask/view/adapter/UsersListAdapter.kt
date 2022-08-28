package com.systemtask.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.systemtask.databinding.UserInfoItemBinding
import com.systemtask.model.UserDetailsItem
import javax.inject.Inject

class UsersListAdapter @Inject constructor() : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private val usersList = ArrayList<UserDetailsItem>()

    inner class ViewHolder(val binding: UserInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserInfoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        holder.binding.apply {
            tvName.text = user.name
            tvEmail.text = user.email

        }
        holder.itemView.setOnClickListener {
            setArticleClickListener?.let {
                it(user)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(dataList: List<UserDetailsItem>) {
        usersList.clear()
        usersList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    private var setArticleClickListener: ((user: UserDetailsItem) -> Unit)? = null

    fun onArticleClicked(listener: (UserDetailsItem) -> Unit) {
        setArticleClickListener = listener
    }
}