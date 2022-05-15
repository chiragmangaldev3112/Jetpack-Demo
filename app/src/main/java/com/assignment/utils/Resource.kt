package com.assignment.utils

import com.assignment.error.ErrorHAndler

// help to Update UI
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val failure: ErrorHAndler) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object Empty : Resource<Nothing>()
}