package com.amier.jelajahrasa.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.api.ApiServiceImpl
import com.amier.jelajahrasa.databinding.ActivityMainBinding
import com.amier.jelajahrasa.ui.base.ViewModelFactory
import com.amier.jelajahrasa.ui.main.adapter.MainRVAdapter
import com.amier.jelajahrasa.ui.main.viewmodel.MainViewModel
import com.amier.jelajahrasa.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter:MainRVAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(MainViewModel::class.java)
        adapter = MainRVAdapter(viewModel)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolbarMain)
        title = "Set DAta"
        binding.rvMain.adapter = adapter
        binding.vm = viewModel
        rvMain.layoutManager = GridLayoutManager(this,2)
    }

    override fun onStart() {
        super.onStart()
        listen()
    }
    private fun listen(){
        viewModel.getList().observe(this, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.overlay.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.appbar.visibility = View.VISIBLE

                    binding.mainSwipe.isRefreshing = false

                    adapter.replaceData(it.data!!.foods)
                    binding.rvMain.startLayoutAnimation()
                }
                Status.ERROR->{
                    binding.overlay.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                    binding.mainSwipe.isRefreshing = false
                }
                Status.LOADING->{
                    binding.overlay.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.mainSwipe.isRefreshing = false
                }
            }
        })
        viewModel.uiEventData.observe(this, Observer {
            when(it){
                0-> startActivity(
                    Intent(this,DetailActivity::class.java)
                        .putExtra("data",viewModel.uiItemData.value))
            }
        })
    }
}
