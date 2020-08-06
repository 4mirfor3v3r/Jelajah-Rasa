package com.amier.jelajahrasa.presentation.ui.auth.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.api.ApiServiceImpl
import com.amier.jelajahrasa.databinding.ActivityLoginBinding
import com.amier.jelajahrasa.external.logDebug
import com.amier.jelajahrasa.external.showToast
import com.amier.jelajahrasa.presentation.base.BaseActivity
import com.amier.jelajahrasa.presentation.base.ViewModelFactory
import com.amier.jelajahrasa.presentation.main.view.MainActivity
import com.amier.jelajahrasa.presentation.ui.auth.viewmodel.LoginViewModel
import com.amier.jelajahrasa.utils.Status
import javax.inject.Inject

class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun createViewModel(): LoginViewModel {
        return ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }
    override fun createBinding(): ActivityLoginBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_login)
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
    private fun observeLiveData(){
        viewModel.setup(this,this)
        viewModel.uiEventData.observe(this, Observer {
            when (it) {
                0 -> {
                    startActivity(Intent(this, RegisterActivity::class.java))
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                }
                2 ->{
                    showToast("Login Success.")
                    startActivity(Intent(this, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)))
                    logDebug("LOGIN SUCCESS")
                }
                3-> {
                    logDebug("Login Error toast two")
                    showToast(viewModel.errorMessage)
                }
            }
        })
    }
}
