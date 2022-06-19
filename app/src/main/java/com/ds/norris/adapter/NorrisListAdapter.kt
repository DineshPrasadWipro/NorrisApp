package com.ds.norris.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ds.norris.R
import com.ds.norris.model.CustomNorrisList

class NorrisListAdapter constructor(
    norrisList: CustomNorrisList, context: Context
) :
    RecyclerView.Adapter<NorrisListAdapter.ViewHolder>() {

    private var mData: CustomNorrisList
    private var mContext: Context

    init {
        mData = norrisList
        mContext = context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.norris_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.norrisId.text = mData.result[position].value
        Glide.with(mContext)
            .load(mData.result[position].url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.norrisImage)
    }

    override fun getItemCount(): Int {
        return mData.result.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var norrisId: TextView
        var lyt: LinearLayout
        var norrisImage: ImageView


        init {
            norrisId = itemView.findViewById(R.id.id)
            lyt = itemView.findViewById(R.id.lyt)
            norrisImage = itemView.findViewById(R.id.norrisImage)

        }
    }

}