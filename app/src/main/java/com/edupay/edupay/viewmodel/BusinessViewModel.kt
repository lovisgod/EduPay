package com.edupay.edupay.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BusinessViewModel(application: Application): ViewModel() {


    /**
     * Factory for constructing AuthViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BusinessViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BusinessViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}