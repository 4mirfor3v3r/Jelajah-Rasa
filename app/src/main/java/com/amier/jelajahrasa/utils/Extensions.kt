package com.amier.jelajahrasa.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.amier.jelajahrasa.data.model.Food
import com.amier.jelajahrasa.ui.main.adapter.MainRVAdapter
import com.amier.jelajahrasa.ui.main.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object Extensions {
    @BindingAdapter(value = ["app:mainList","app:vm"], requireAll = false)
    @JvmStatic
    fun setmainList(recyclerView: RecyclerView, repoData: List<Food>?, vm: MainViewModel){
        if (repoData != null){
            recyclerView.adapter =MainRVAdapter()
            with(recyclerView.adapter as MainRVAdapter){
                viewModel = vm
                replaceData(repoData)
            }
        }
    }

    private val picasso: Picasso
        get() = Picasso.get()

    private fun ImageView.load(path:String, request:(RequestCreator) ->RequestCreator){
        request(picasso.load(path)).into(this)
    }

    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageView,url:String?){
        if (url!=null){
            view.load(url){requestCreator ->
                requestCreator.fit().centerCrop()
            }
        }
    }
}