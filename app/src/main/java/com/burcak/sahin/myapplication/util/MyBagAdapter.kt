package com.burcak.sahin.myapplication.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.ui.home.ui.mybag.MyBagClickListener
import com.burcak.sahin.myapplication.ui.home.ui.mybag.data.MyBagModel


class MyBagAdapter(private val myBagClickListener: MyBagClickListener) : ListAdapter<MyBagModel, MyBagAdapter.ChildrenHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_my_bag,
            parent,
            false
        )
        return ChildrenHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ChildrenHolder, position: Int) {
        with(getItem(position)) {
            holder.price.text = this.price
            holder.type.text = this.name
            holder.count.text = this.count.toString()
            holder.photo.updateWithUrl(this.path, holder.photo)
            holder.plus.setOnClickListener {
                holder.count.text = (this.count+1).toString()
                isPlus(this)
            }
            holder.minus.setOnClickListener {
                holder.count.text = (this.count-1).toString()
                isMinus(this)
            }
        }
    }
    private fun isPlus(myBagModel: MyBagModel) {
        myBagClickListener.isPlus(myBagModel)
    }
    private fun isMinus(myBagModel: MyBagModel) {
        myBagClickListener.isMinus(myBagModel)
    }
    inner class ChildrenHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val type: TextView = itemView.findViewById(R.id.textViewType)
        val count: TextView = itemView.findViewById(R.id.textViewAmount)
        val price: TextView = itemView.findViewById(R.id.textViewPrice)
        val photo: ImageView = itemView.findViewById(R.id.imageView4)
        val minus: Button = itemView.findViewById(R.id.buttonDecrease)
        val plus: Button = itemView.findViewById(R.id.buttonIncrease)
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<MyBagModel>() {
    override fun areItemsTheSame(oldItem: MyBagModel, newItem: MyBagModel): Boolean {
        return newItem == oldItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: MyBagModel,
        newItem: MyBagModel
    ): Boolean {
        return oldItem == newItem
    }
}