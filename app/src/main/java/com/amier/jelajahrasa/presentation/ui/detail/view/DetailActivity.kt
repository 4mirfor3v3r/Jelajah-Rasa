package com.amier.jelajahrasa.presentation.ui.detail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.databinding.ActivityDetailBinding
import com.amier.jelajahrasa.presentation.base.BaseActivity
import com.amier.jelajahrasa.presentation.ui.detail.viewmodel.DetailViewModel
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun createViewModel(): DetailViewModel {
        return ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }

    override fun createBinding(): ActivityDetailBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
