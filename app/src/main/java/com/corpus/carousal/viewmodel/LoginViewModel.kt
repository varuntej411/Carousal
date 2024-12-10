package com.corpus.carousal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data

}