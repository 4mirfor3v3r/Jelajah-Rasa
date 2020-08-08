package com.amier.jelajahrasa.presentation.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.databinding.ActivityMainBinding
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.external.extensions.logDebug
import com.amier.jelajahrasa.external.extensions.showToast
import com.amier.jelajahrasa.presentation.base.BaseActivity
import com.amier.jelajahrasa.presentation.ui.detail.view.DetailActivity
import com.amier.jelajahrasa.presentation.ui.main.adapter.MainRvFoodsAction
import com.amier.jelajahrasa.presentation.ui.main.adapter.MainRvFoodsAdapter
import com.amier.jelajahrasa.presentation.ui.main.viewmodel.MainViewModel
import com.amier.jelajahrasa.external.Constants
import com.amier.jelajahrasa.external.events.Status
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    private var adapter = MainRvFoodsAdapter()
    private var likedArray = ArrayList<Int>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun createViewModel(): MainViewModel {
        return ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun createBinding(): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setSupportActionBar(toolbarMain)

        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.pagingList?.observe(this, Observer {
            try {
                logDebug("SUBMITED")
                adapter.submitList(it)
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()
            }
        })
        viewModel.getLoadingState()?.observe(this, Observer { status ->
            if (status != null)
                when (status) {
                    Status.SUCCESS -> {
                        viewModel.isLoading.set(false)
                        binding.rvMain.startLayoutAnimation()
                        logDebug("LOADING STATE SUCCESS")
                    }
                    Status.ERROR -> {
                        viewModel.isLoading.set(false)
                        viewModel.getMessageValue()?.value?.let { showToast(it) }
                        logDebug("LOADING STATE ERROR")
                    }
                    Status.LOADING -> {
                        logDebug("LOADING STATE LOADING")
                    }
                }
        })
    }

    private fun setupRecyclerView() {
        binding.rvMain.layoutManager = GridLayoutManager(this, 2)
        adapter.setOnItemClick(object : MainRvFoodsAction {
            override fun onItemClick(data: Food) { onItemClicked(data) }

            override fun onLikesClick(view: View, data: Food) { onLikesClicked(view, data) }
        })
        binding.rvMain.adapter = adapter
    }

    fun onItemClicked(data: Food) {
        startActivity(Intent(this, DetailActivity::class.java)
                .putExtra(Constants.DETAILACTIVITY_EXTRA, data))
    }
    fun onLikesClicked(view: View, data: Food){

    }
}
