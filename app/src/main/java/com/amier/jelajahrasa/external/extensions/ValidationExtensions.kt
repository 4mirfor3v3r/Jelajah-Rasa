package com.amier.jelajahrasa.external.extensions

import android.content.Context
import android.util.Patterns
import com.amier.jelajahrasa.R

class ValidationModel(val isValid: Boolean, val message: String)

fun String.validateName(context: Context): ValidationModel {
    return if (this.trim().isEmpty()) {
        ValidationModel(
            false,
            context.getString(R.string.all_name_empty)
        )
    } else if (this.length<3) {
        ValidationModel(
            false,
            context.getString(R.string.all_name_min)
        )
    } else {
        ValidationModel(true, "")
    }
}

fun String.validateEmail(context: Context): ValidationModel {
    return if (this.trim().isEmpty()) {
        ValidationModel(
            false,
            context.getString(R.string.all_email_empty)
        )
    } else if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        ValidationModel(
            false,
            context.getString(R.string.all_email_format_wrong)
        )
    } else {
        ValidationModel(true, "")
    }
}

//  context for harcoded string
fun String.validatePassword(context: Context): ValidationModel {
    // require combination number and text
    val pattern = "[0-9]".toRegex()
    val pattern2 = "[a-z]|[A-Z]".toRegex()

    return if (this.isEmpty()) {
        ValidationModel(
            false,
            context.getString(R.string.all_pasword_empty)
        )
    } else if (this.length < 5) {
        ValidationModel(
            false,
            context.getString(R.string.all_password_min)
        )
    } else if (!pattern.containsMatchIn(this) || !pattern2.containsMatchIn(this)) {
        ValidationModel(
            false,
            context.getString(R.string.all_password_combination)
        )
    } else {
        ValidationModel(true, "")
    }
}
