package com.assignment.utils

fun <T> success(r: T) = Either.Success(r)
fun <T> failure(l: T) = Either.Failure(l)

// help to manage send data to Viewmodel or Ui
sealed class Either<out L, out R> {
    data class Failure<out L>(val l: L) : Either<L, Nothing>()
    data class Success<out R>(val r: R) : Either<Nothing, R>()

    fun isFailure(): Boolean = this is Failure

    fun isSuccess(): Boolean = this is Success

    fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T {
        return when (this) {
            is Failure -> fnL(l)
            is Success -> fnR(r)
        }
    }

    fun getOrNull(): R? {
        return if (this is Success) {
            r
        } else {
            null
        }
    }
}

fun <R, T : R> Either<Any, T>.getOrElse(defaultValue: R): R {
    return when (this) {
        is Either.Success -> r
        else -> defaultValue
    }
}