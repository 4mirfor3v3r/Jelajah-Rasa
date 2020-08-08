package com.amier.jelajahrasa.presentation.ui.auth.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.databinding.ActivitySplashBinding
import com.amier.jelajahrasa.presentation.base.BaseActivity
import com.amier.jelajahrasa.presentation.ui.auth.viewmodel.SplashViewModel
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel,ActivitySplashBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun createViewModel(): SplashViewModel {
        return ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    override fun createBinding(): ActivitySplashBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            checkIsLoggedIn()
        },2000)
    }
}
