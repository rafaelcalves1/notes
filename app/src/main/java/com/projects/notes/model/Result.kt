package com.projects.notes.model

sealed class Result<out T : Any>() {

    data class ResultSuccess<out T : Any>(val successData: T) : Result<T>()

    data class ResultError<out T : Any>(val throwable: Throwable) : Result<T>()

    object Loading : Result<Nothing>()

}
