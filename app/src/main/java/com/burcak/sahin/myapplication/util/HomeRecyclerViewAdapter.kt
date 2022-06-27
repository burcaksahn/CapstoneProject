package com.burcak.sahin.myapplication.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel

class HomeRecyclerViewAdapter(val productClickListener: ProductClickListener) : ListAdapter<ProductModel, HomeRecyclerViewAdapter.ChildrenHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_new_prod,
            parent,
            false
        )
        return ChildrenHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChildrenHolder, position: Int) {
        with(getItem(position)) {
           holder.productPhoto.updateWithUrl(this.image.toString(), holder.productPhoto)
            holder.itemView.setOnClickListener {
                productClickListener.isProductCliked(this)
            }
        }
    }
    inner class ChildrenHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val productPhoto: ImageView = itemView.findViewById(R.id.new_item_image)
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