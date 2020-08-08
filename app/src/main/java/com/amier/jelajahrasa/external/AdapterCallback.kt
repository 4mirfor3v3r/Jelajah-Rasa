package com.amier.jelajahrasa.external

import androidx.recyclerview.widget.DiffUtil
import com.amier.jelajahrasa.datas.model.Food

object AdapterCallback {
    val DiffFoodCallback = object : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.id == newItem.id
        }
    }
}