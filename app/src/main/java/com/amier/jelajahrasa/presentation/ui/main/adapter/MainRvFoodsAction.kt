package com.amier.jelajahrasa.presentation.ui.main.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.amier.jelajahrasa.datas.model.Food

interface MainRvFoodsAction {
    fun onItemClick(data:Food)
    fun onLikesClick(view: View, data: Food)
}