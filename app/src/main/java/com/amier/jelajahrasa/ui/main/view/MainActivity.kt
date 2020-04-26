package com.amier.jelajahrasa.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.api.ApiServiceImpl
import com.amier.jelajahrasa.databinding.ActivityMainBinding
import com.amier.jelajahrasa.ui.base.ViewModelFactory
import com.amier.jelajahrasa.ui.main.adapter.MainRVAdapter
import com.amier.jelajahrasa.ui.main.viewmodel.MainViewModel
import com.amier.jelajahrasa.utils.Constants
import com.amier.jelajahrasa.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MainRVAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var likedArray = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(MainViewModel::class.java)

        adapter = MainRVAdapter(viewModel,likedArray)

        setSupportActionBar(toolbarMain)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvMain.adapter = adapter
        binding.vm = viewModel
        rvMain.layoutManager = GridLayoutManager(this, 2)

    }

    override fun onStart() {
        super.onStart()
        listen()
    }

    private fun listen() {
        viewModel.likedArray.observe(this, Observer {
            Log.e("LIKED ARRAY",likedArray.toString())
            likedArray = it
            adapter.likedId = likedArray
            adapter.notifyDataSetChanged()
        })
        viewModel.getList().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    view(false, 8)
                    binding.appbar.visibility = View.VISIBLE

                    adapter.replaceData(it.data!!.foods)
                    binding.rvMain.startLayoutAnimation()
                }
                Status.ERROR -> {
                    view(false, 8)
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    view(false, 0)
                }
            }
        })
        viewModel.uiEventData.observe(this, Observer {
            when (it) {
                0 -> startActivity(
                    Intent(this, DetailActivity::class.java)
                        .putExtra(Constants.DETAILACTIVITY_EXTRA, viewModel.uiItemData.value)
                )
            }
        })
        viewModel.getLikedId().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    when(it.data?.status){
                        "error" ->{

                        }
                        "ok" ->{
                            adapter.likedId = likedArray
//                            adapter.notifyItemChanged(it.data.food.id)
                            adapter.notifyDataSetChanged()
                            Log.e("ADAPTER LIKED ID",adapter.likedId.toString())
                        }
                    }
                }
                Status.ERROR -> {
                    view(false, 8)
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    view(false, 0)
                }
            }
        })
    }

    fun view(enable: Boolean, visibility: Int) {
        binding.mainSwipe.isRefreshing = enable
        binding.overlay.visibility = visibility
        binding.progressBar.visibility = visibility
    }
}
