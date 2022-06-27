package com.burcak.sahin.myapplication.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel

class CategoryDetailAdapter() : ListAdapter<ProductModel, CategoryDetailAdapter.ChildrenHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_category_detail,
            parent,
            false
        )
        return ChildrenHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChildrenHolder, position: Int) {
        with(getItem(position)) {
           // holder.category.text = this
            holder.type.text = this.category
            holder.size.text = "XL"
            holder.price.text = price.toString()
            holder.photo.updateWithUrl(this.image.toString(), holder.photo)
        }
    }

    inner class ChildrenHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val type: TextView = itemView.findViewById(R.id.textViewType)
        val size: TextView = itemView.findViewById(R.id.textViewSize)
        val price: TextView = itemView.findViewById(R.id.textViewPrice)
        val photo: ImageView = itemView.findViewById(R.id.imageView3)
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return newItem == oldItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: ProductModel,
        newItem: ProductModel
    ): Boolean {
        return oldItem == newItem
    }
}