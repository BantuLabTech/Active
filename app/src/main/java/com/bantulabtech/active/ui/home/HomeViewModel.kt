package com.bantulabtech.active.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    private val _loggedIn = MutableLiveData<Boolean>().apply {
        value = false
    }
    val text: LiveData<String> = _text
    val loggedIn: LiveData<Boolean> = _loggedIn
}