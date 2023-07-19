package com.yifan.fewizard.ui.activity.main

import android.os.Build
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yifan.fewizard.base.BaseViewModel
import com.yifan.fewizard.bean.FuelBean
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime

class MainViewModel : BaseViewModel() {

    private val _tag = "MainViewModel"

    private var fuelLiveData = MutableLiveData<String>()

    private val fuelBean = FuelBean()

    fun setFuelLiveData(s: Float) {
        oldFuelSaveDb()
        fuelLiveData.value = getNoMoreThanTwoDigits(s)
        fuelBean.fuelC = fuelLiveData.value.toString()
        fuelBean.date = LocalDateTime.now().toString()
    }

    private fun oldFuelSaveDb() {
        if (fuelBean.date == null) {
            return
        }
        Log.d(_tag, "oldFuelSaveDb...")
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