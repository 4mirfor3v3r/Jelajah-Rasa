package com.amier.jelajahrasa.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amier.jelajahrasa.data.model.Food
import com.amier.jelajahrasa.ui.main.adapter.MainRVAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object Extensions {
//    @BindingAdapter("app:mainList")
//    @JvmStatic
//    fun setmainList(recyclerView: GridRecyclerView, repoData: List<Food>?
////                    , vm: MainViewModel
//    ){
//        if (repoData != null){
//            recyclerView.adapter =MainRVAdapter()
//            with(recyclerView.adapter as MainRVAdapter){
//                Log.e("LISTED",repoData.toString())
//                replaceData(repoData)
//            }
//        }
//        Log.e("LISTEDNUL",repoData.toString())
//    }

    private val picasso: Picasso
        get() = Picasso.get()

    private fun ImageView.load(path:String, request:(RequestCreator) ->RequestCreator){
        request(picasso.load(path)).into(this)
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView,url:String?){
        if (url!=null){
            view.load(url){requestCreator ->
                requestCreator.fit().centerCrop()
            }
        }
    }
}