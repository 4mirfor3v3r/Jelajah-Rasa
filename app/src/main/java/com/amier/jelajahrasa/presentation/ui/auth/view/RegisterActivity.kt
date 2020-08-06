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
import com.amier.jelajahrasa.databinding.ActivityRegisterBinding
import com.amier.jelajahrasa.presentation.base.ViewModelFactory
import com.amier.jelajahrasa.presentation.main.view.MainActivity
import com.amier.jelajahrasa.presentation.ui.auth.viewmodel.RegisterViewModel
import com.amier.jelajahrasa.utils.Status

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel: RegisterViewModel
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
                    view(true,8)
                    if (it.data != null){
                        if (it.data.status == "error"){
                            Toast.makeText(this, "Error :${it.data.msg}", Toast.LENGTH_SHORT).show()
                        }else if (it.data.status == "ok"){
                            val dat = it.data.user
                            if (dat != null) {
                                viewModel.saveToPreferences(dat)
                                Toast.makeText(this, "Login Success.", Toast.LENGTH_LONG).show()
                                startActivity(
                                    Intent(
                                        this,
                                        MainActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK))
                                )
                            }
                        }
                    }
                }
                Status.ERROR ->{
                    view(true,8)
                }
                Status.LOADING ->{
                    view(false,0)
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
    fun view(enable: Boolean, visibility: Int) {
        binding.layoutRegister.isEnabled = enable
        binding.overlay.visibility = visibility
        binding.progressBar.visibility = visibility
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }
}
