package com.amier.jelajahrasa.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.R
import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.api.ApiServiceImpl
import com.amier.jelajahrasa.databinding.ActivityLoginBinding
import com.amier.jelajahrasa.ui.base.ViewModelFactory
import com.amier.jelajahrasa.ui.main.viewmodel.LoginViewModel
import com.amier.jelajahrasa.utils.Status

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(LoginViewModel::class.java)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        binding.vm = viewModel

        listen()
    }
    private fun listen(){
        viewModel.getUser().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    binding.layoutLogin.isEnabled = true
                    binding.overlay.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    if (it.data != null){
                        if (it.data.status == "error"){
                            Toast.makeText(this, "Error :${it.data.msg}",Toast.LENGTH_SHORT).show()
                        }else if (it.data.status == "ok"){
                            val dat = it.data.user
                            if (dat != null) {
                                Toast.makeText(this, "Login Success :\\nData: [${dat.name} ${dat.email} ${dat.password}]", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                Status.ERROR -> {
                    binding.layoutLogin.isEnabled = true
                    binding.overlay.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.layoutLogin.isEnabled = false
                    binding.overlay.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
        viewModel.uiEventData.observe(this, Observer {
            when(it){
                0-> {
                    startActivity(Intent(this,RegisterActivity::class.java))
                    overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
                }
                1-> viewModel.performLogin(binding.etLoginEmail.text.toString(),binding.etLoginPassword.text.toString())
            }
        })
    }
}
