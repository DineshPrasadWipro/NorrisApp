package com.ds.norris.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ds.norris.R

class CategoryAdapter constructor(
    categories: List<String>,
    clickListener: ItemClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var mData: List<String>
    private var mClickListener: ItemClickListener? = null

    init {
        mData = categories
        mClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryItem.text = mData[position]
        holder.lyt.setOnClickListener {
            mClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryItem: TextView
        var lyt: LinearLayout


        init {
            categoryItem = itemView.findViewById(R.id.categoryItem)
            lyt = itemView.findViewById(R.id.lyt)

        }
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }


}