package com.assignment.error

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.assignment.R

// used to manage error in case of API
sealed class ErrorHAndler(@StringRes val key: Int) {

    object UnexpectedErrorHAndler : ErrorHAndler(R.string.unexpected_error)

    @Composable
    fun translate(): String {
        return stringResource(key)
    }
}
