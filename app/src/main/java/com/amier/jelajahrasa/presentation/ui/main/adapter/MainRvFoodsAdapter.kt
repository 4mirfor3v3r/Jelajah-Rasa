package com.amier.jelajahrasa.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.databinding.ItemMainBinding
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.external.AdapterCallback
import com.amier.jelajahrasa.external.events.Status
import java.lang.Exception

class MainRvFoodsAdapter :
    PagedListAdapter<Food, RecyclerView.ViewHolder>(AdapterCallback.DiffFoodCallback) {
    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOAD = 2
    }

    var loadingState = Status.LOADING
    private var lastPosition = -1

    private var onItemClicked: MainRvFoodsAction? = null
    fun setOnItemClick(onItemClick: MainRvFoodsAction) {
        this.onItemClicked = onItemClick
    }

    class MainRvFoodsHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(pagingModel: Food, onItemClicked: MainRvFoodsAction?) {
            binding.data = pagingModel
            binding.action = onItemClicked

            binding.executePendingBindings()
        }
    }

    class LoadMoreHolder(private val x: View) : RecyclerView.ViewHolder(x) {
        fun setVisible(boolean: Boolean) {
            x.visibility = if (boolean) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding: ItemMainBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_main, parent, false)
            MainRvFoodsHolder(binding)
        } else {
            val x = inflater.inflate(R.layout.item_load_more, parent, false)
            LoadMoreHolder(x)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainRvFoodsHolder) {
            try {
                val food = getItem(holder.adapterPosition)
                food?.let { holder.bindItem(food, onItemClicked) }
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()
            }
        } else if (holder is LoadMoreHolder) {
            holder.setVisible(hasFooter())
        }
    }

    override fun submitList(pagedList: PagedList<Food>?) {
        try {
            if (pagedList != null && currentList != null) {
                if (currentList!!.positionOffset >= pagedList.positionOffset)
                    this.currentList?.dataSource?.invalidate()
                else
                    super.submitList(pagedList)
            }else
                super.submitList(pagedList)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) VIEW_TYPE_ITEM else VIEW_TYPE_LOAD
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && loadingState == Status.LOADING
    }
}