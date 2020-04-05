package com.amier.jelajahrasa.ui.main.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.data.model.Food
import com.amier.jelajahrasa.databinding.ItemMainBinding
import com.amier.jelajahrasa.ui.listener.ItemMainClickListener
import com.amier.jelajahrasa.ui.main.viewmodel.MainViewModel

class MainRVAdapter():RecyclerView.Adapter<MainRVAdapter.Holder>() {

    private var list:ArrayList<Food>? = arrayListOf()
    lateinit var viewModel:MainViewModel

    class Holder(itemMainBinding:ItemMainBinding):RecyclerView.ViewHolder(itemMainBinding.root) {
        private val binding = itemMainBinding
        fun bindRows(data:Food,itemClickListener: ItemMainClickListener) {
            binding.data = data
            binding.action = itemClickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemMainBinding = DataBindingUtil.inflate<ItemMainBinding>(LayoutInflater.from(parent.context),
            R.layout.item_main,parent,false)
        return Holder(itemMainBinding)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list?.get(position)
        val actionListener = object :ItemMainClickListener{
            override fun onItemClick() {
                viewModel.uiItemData.value = data
                viewModel.onClickEvent(0)
            }

            override fun onLikesClick() {
                viewModel.setLikesOrNot(data?.id)
            }
        }
        if (data !=null) {
            holder.bindRows(data,actionListener)
        }
    }

    fun replaceData(repoD:List<Food>){
        setList(repoD)
    }

    private fun setList(repoD: List<Food>) {
        this.list?.addAll(repoD)
        notifyDataSetChanged()
    }
}