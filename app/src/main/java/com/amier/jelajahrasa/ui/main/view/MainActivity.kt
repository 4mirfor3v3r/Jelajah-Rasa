package com.amier.jelajahrasa.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.api.ApiServiceImpl
import com.amier.jelajahrasa.databinding.ActivityMainBinding
import com.amier.jelajahrasa.ui.base.ViewModelFactory
import com.amier.jelajahrasa.ui.main.viewmodel.MainViewModel
import com.amier.jelajahrasa.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel

        rvMain.layoutManager = GridLayoutManager(this,2,(rvMain.layoutManager as GridLayoutManager).orientation,false)
        val i = Intent()
        listen()
    }
    private fun listen(){
        viewModel.getList().observe(this, Observer {
            when(it.status){
                Status.SUCCESS->{}
                Status.ERROR->{}
                Status.LOADING->{}
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
