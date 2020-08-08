package com.amier.jelajahrasa.presentation.ui.auth.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.databinding.ActivityRegisterBinding
import com.amier.jelajahrasa.external.extensions.logDebug
import com.amier.jelajahrasa.external.extensions.showToast
import com.amier.jelajahrasa.presentation.base.BaseActivity
import com.amier.jelajahrasa.presentation.ui.main.view.MainActivity
import com.amier.jelajahrasa.presentation.ui.auth.viewmodel.RegisterViewModel
import javax.inject.Inject

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun createViewModel(): RegisterViewModel {
        return ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
    }

    override fun createBinding(): ActivityRegisterBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_register)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }

    override fun onStart() {
        super.onStart()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.setup(this, this)
        viewModel.uiEventData.observe(this, Observer {
            when (it) {
                0 -> {
                    onBackPressed()
                }
                2 -> {
                    showToast("Login Success.")
                    startActivity(
                        Intent(this, MainActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK))
                    )
                    logDebug("LOGIN SUCCESS")
                }
                3 -> {
                    logDebug("Login Error toast two")
                    showToast(viewModel.errorMessage)
                }
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}
