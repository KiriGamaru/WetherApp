package com.example.wetherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val livaDataCurrent = MutableLiveData<String>()
    val livaDataList = MutableLiveData<List<String>>()
}