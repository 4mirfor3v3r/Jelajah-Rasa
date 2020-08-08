package com.amier.jelajahrasa.presentation.base

import com.amier.jelajahrasa.R
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.amier.jelajahrasa.App
import com.amier.jelajahrasa.external.extensions.logDebug
import com.amier.jelajahrasa.presentation.ui.auth.view.LoginActivity
import com.amier.jelajahrasa.presentation.ui.main.view.MainActivity
import com.amier.jelajahrasa.external.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<VM : ViewModel, BINDING : ViewDataBinding> : DaggerAppCompatActivity() {

    protected lateinit var viewModel:VM
    protected lateinit var binding:BINDING

    @Inject
    lateinit var daggerViewModelfactory: DaggerViewModelFactory

    protected abstract fun createViewModel():VM
    protected abstract fun createBinding():BINDING
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        binding = createBinding()
    }

    protected fun checkIsLoggedIn() {
        val res = App.prefHelper?.getBoolean(Constants.IS_LOGIN)
        res?.let {
            if (!it) {
                startActivity(Intent(this, LoginActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK))
                )
            }
            else{
                startActivity(Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK))
                )
            }
            logDebug("IS LOGGED IN $it")
        }
    }

    open fun showSnackBarInternetConnection() {
        val view = findViewById<View>(android.R.id.content)
        Snackbar.make(view, getString(R.string.all_no_internet), Snackbar.LENGTH_LONG).show()
    }

    open fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        val isConnect = activeNetworkInfo != null && activeNetworkInfo.isConnected
        if (!isConnect) showSnackBarInternetConnection()
        return isConnect
    }
}