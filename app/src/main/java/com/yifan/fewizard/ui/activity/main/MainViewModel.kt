package com.yifan.fewizard.ui.activity.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yifan.fewizard.base.BaseViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class MainViewModel : BaseViewModel() {


    private var fuelLiveData = MutableLiveData<String>()

    fun setFuelLiveData(s: Float) {
        fuelLiveData.value = getNoMoreThanTwoDigits(s)
    }

    fun getFuelLiveData(): LiveData<String> {
        return fuelLiveData
    }

    /**
     * 对入参保留最多两位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    private fun getNoMoreThanTwoDigits(number: Float): String {
        val format = DecimalFormat("0.00")
        //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
        format.roundingMode = RoundingMode.FLOOR
        return format.format(number)
    }
}