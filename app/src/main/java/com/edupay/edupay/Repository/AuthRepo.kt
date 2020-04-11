package com.edupay.edupay.Repository

import com.edupay.edupay.RetrofitClient.Network
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AuthRepo(private val network: Network) {
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
}