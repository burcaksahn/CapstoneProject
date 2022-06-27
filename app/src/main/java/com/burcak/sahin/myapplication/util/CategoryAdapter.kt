package com.burcak.sahin.myapplication.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.ui.home.ui.dashboard.CategoryListener

class CategoryAdapter(val categoryListener: CategoryListener) :
    ListAdapter<String, CategoryAdapter.ChildrenHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_choose_category,
            parent,
            false
        )
        return ChildrenHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChildrenHolder, position: Int) {
        with(getItem(position)) {
            holder.category.text = this

            holder.category.setOnClickListener {
                categoryListener.clicked(this)
            }
        }
    }

    inner class ChildrenHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val category: TextView = itemView.findViewById(R.id.textViewCategory)
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return newItem == oldItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }
}