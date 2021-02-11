package com.core.kamalpagging.presentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.core.kamalpagging.R
import com.core.kamalpagging.data.model.User
import com.core.kamalpagging.databinding.ItemRowBinding

class UserAdapter (private val context: Context) : PagedListAdapter<User, UserAdapter.MyViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemRowBinding = DataBindingUtil.inflate(inflater, R.layout.item_row,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.user = getItem(position)
    }

    class MyViewHolder(val itemBinding: ItemRowBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemRowBinding? = null

        init {
            this.binding = itemBinding
        }

    }
    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                newItem == oldItem
        }
    }

}