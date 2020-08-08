package com.amier.jelajahrasa.external

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("errorText")
    fun TextInputLayout.setErrorMessage(errorMessage: String?) {
        this.isErrorEnabled = true
        this.error = errorMessage
    }

    @JvmStatic
    @BindingAdapter("helperText")
    fun TextInputLayout.setHelperTextMessage(successMessage:String?){
        this.isHelperTextEnabled = true
        this.helperText = successMessage
    }

    private val picasso: Picasso
        get() = Picasso.get()

    private fun ImageView.load(path:String, request:(RequestCreator) -> RequestCreator){
        request(picasso.load(path)).into(this)
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url:String?){
        if (url!=null){
            view.load(url){requestCreator ->
                requestCreator.fit().centerCrop()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setRefresh")
    fun SwipeRefreshLayout.setRefresh(isRefresh: Boolean) {
        this.isRefreshing = isRefresh
    }

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
}