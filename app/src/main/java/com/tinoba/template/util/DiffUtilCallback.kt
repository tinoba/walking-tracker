package com.tinoba.template.util

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem === newItem

    override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
}

