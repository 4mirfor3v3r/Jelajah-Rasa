package com.amier.jelajahrasa.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.data.model.Food
import com.amier.jelajahrasa.databinding.ItemMainBinding
import com.amier.jelajahrasa.ui.listener.ItemMainClickListener
import com.amier.jelajahrasa.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_main.view.*

class MainRVAdapter(val viewModel: MainViewModel,var likedId:ArrayList<Int>?):RecyclerView.Adapter<MainRVAdapter.Holder>() {

    private var list:ArrayList<Food>? = arrayListOf()

    class Holder(itemMainBinding:ItemMainBinding):RecyclerView.ViewHolder(itemMainBinding.root) {
        private val binding = itemMainBinding
        fun bindRows(data: Food, itemClickListener: ItemMainClickListener) {
            binding.data = data
            binding.action = itemClickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemMainBinding:ItemMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_main,parent,false)
        return Holder(itemMainBinding)
    }

    override fun getItemCount(): Int {
        return try {
            list?.size!!
        }catch (e:NullPointerException){
            0
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val food = list?.get(position)
        val actionListener = object :ItemMainClickListener{
            override fun onItemClick() {
                viewModel.uiItemData.value = food
                viewModel.onClickEvent(0)
            }

            override fun onLikesClick() {
                if (food != null) {
                    if (likedId?.contains(food.id)!!) {
                        holder.itemView.ivMainFavourite.setImageResource(R.drawable.ic_likes_outline)
                        food.likes--
                        viewModel.removeFromLikes(food.id)
//                        notifyItemChanged(position)
                    }
                    else {
                        holder.itemView.ivMainFavourite.setImageResource(R.drawable.ic_likes_filled)
                        food.likes++
                        viewModel.addToLikes(food.id)
//                        notifyItemChanged(position)
                    }
                }
            }
        }
        if (food !=null) {
            if (likedId?.contains(food.id)!!){
                holder.itemView.ivMainFavourite.setImageResource(R.drawable.ic_likes_filled)
            }else{
                holder.itemView.ivMainFavourite.setImageResource(R.drawable.ic_likes_outline)
            }

            holder.bindRows(food,actionListener)
        }
    }

    fun replaceData(repoD:List<Food>){
        setList(repoD)
    }

    private fun setList(repoD: List<Food>) {
        this.list?.clear()
        this.list?.addAll(repoD)
        notifyDataSetChanged()
    }
}