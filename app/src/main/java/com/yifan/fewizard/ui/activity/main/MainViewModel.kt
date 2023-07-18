package com.yifan.fewizard.ui.activity.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yifan.fewizard.base.BaseViewModel

class MainViewModel : BaseViewModel() {


    private var fuelLiveData = MutableLiveData<String>()

    fun setFuelLiveData(s: String) {
        fuelLiveData.value = s
    }

    fun getFuelLiveData(): LiveData<String> {
        return fuelLiveData
    }
}