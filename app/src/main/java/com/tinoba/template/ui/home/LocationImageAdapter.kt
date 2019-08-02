package com.tinoba.template.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tinoba.template.R
import com.tinoba.template.util.DiffUtilCallback
import kotlinx.android.synthetic.main.location_list_item.view.*

class LocationImageAdapter(private val inflater: LayoutInflater, private val context: Context) :
    ListAdapter<String, LocationImageAdapter.ItemViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(inflater.inflate(R.layout.location_list_item, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.fillViews(getItem(position), context)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillViews(url: String, context: Context) {
            if (url.isNotEmpty()) {
                Glide.with(context)
                    .load(url)
                    .into(itemView.news_list_item_image)
            }
        }
    }
}