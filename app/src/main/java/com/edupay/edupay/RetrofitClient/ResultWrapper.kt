package com.edupay.edupay.RetrofitClient

import com.edupay.edupay.model.GeneralResponse

sealed class ResultWrapper<out T> {
    data class Success<T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: GeneralResponse? = null) :
        ResultWrapper<Nothing>()

    data class NetworkError(val message: String = "Error during operation please check your network") : ResultWrapper<Nothing>()
}