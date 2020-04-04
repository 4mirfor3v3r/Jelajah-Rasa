package com.amier.jelajahrasa.ui.main.view

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
import com.amier.jelajahrasa.databinding.ActivityRegisterBinding
import com.amier.jelajahrasa.ui.base.ViewModelFactory
import com.amier.jelajahrasa.ui.main.viewmodel.RegisterViewModel
import com.amier.jelajahrasa.utils.Status

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel:RegisterViewModel
    private lateinit var binding:ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(
            RegisterViewModel::class.java)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        binding.vm = viewModel

        listen()
    }
    private fun listen(){
        viewModel.getUser().observe(this, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    binding.layoutRegister.isEnabled = true
                    binding.overlay.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    if (it.data != null){
                        if (it.data.status == "error"){
                            Toast.makeText(this, "Error :${it.data.msg}", Toast.LENGTH_SHORT).show()
                        }else if (it.data.status == "ok"){
                            val dat = it.data.user
                            if (dat != null) {
                                Toast.makeText(this, "Login Success :\\nData: [${dat.name} ${dat.email} ${dat.password}]", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                Status.ERROR ->{
                    binding.layoutRegister.isEnabled = true
                    binding.overlay.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING ->{
                    binding.layoutRegister.isEnabled = false
                    binding.overlay.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
        viewModel.uiEventData.observe(this, Observer {
            when(it){
                0-> onBackPressed()
                1-> viewModel.performRegister(binding.etRegisterName.text.toString(),
                    binding.etRegisterEmail.text.toString(),
                    binding.etRegisterPassword.text.toString())
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }
}
