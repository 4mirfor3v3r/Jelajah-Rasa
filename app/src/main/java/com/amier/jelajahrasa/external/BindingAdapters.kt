package com.amier.jelajahrasa.external

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("errorText")
    fun TextInputLayout.setErrorMessage(errorMessage: String?) {
        this.isErrorEnabled = true
        this.error = errorMessage
    }

    @JvmStatic
    @BindingAdapter("helperText")
    fun TextInputLayout.setHelperTextMessage(successMessage:String?){
        this.isHelperTextEnabled = true
        this.helperText = successMessage
    }
}