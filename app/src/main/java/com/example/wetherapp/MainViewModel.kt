package com.example.wetherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wetherapp.adapters.WeatherModel

class MainViewModel : ViewModel() {
    val livaDataCurrent = MutableLiveData<WeatherModel>()
    val livaDataList = MutableLiveData<List<WeatherModel>>()
}