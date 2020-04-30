package com.edupay.edupay.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BusinessViewModel(application: Application): ViewModel() {

//    var period_text = "Choose Academic Period"
    private val period_text = MutableLiveData<String>()
    val _periodText:LiveData<String> get() = period_text
    private val class_text = MutableLiveData<String>()
    val _classText:LiveData<String> get() = class_text

    private val term_text = MutableLiveData<String>()
    val _termText:LiveData<String> get() = term_text

    init {
        period_text.value = "Choose Academic Period"
        class_text.value = "Choose Class"
    }

//    fun getPeriodText():String {
//        this.period_text.observe()
//    }
//    fun getClassText():String {
//        return this.class_text
//    }

    fun setPeriodText(text: String) {
        this.period_text.value = text
        println(this.period_text.value)
    }

    fun setClassText(text:String) {
        this.class_text.value = text
        println(this.class_text.value)
    }

    fun setTermText(text:String) {
        this.term_text.value = text
        println(this.term_text.value)
    }


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