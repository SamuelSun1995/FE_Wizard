package com.yifan.fewizard.ui.activity.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yifan.fewizard.base.BaseViewModel
import com.yifan.fewizard.entity.FuelEntity
import com.yifan.fewizard.manager.DbManager
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime

class MainViewModel : BaseViewModel() {

    private val _tag = "MainViewModel"

    private var fuelLiveData = MutableLiveData<String>()

    private val fuelBean = FuelEntity()

    fun setFuelLiveData(refuel: String, mileage: String) {
        oldFuelSaveDb()
        fuelLiveData.value =
            getNoMoreThanTwoDigits(refuel.toFloat().div(mileage.toFloat()).times(100))
        fuelBean.fuelC = fuelLiveData.value.toString()
        fuelBean.date = LocalDateTime.now().toString()
        fuelBean.refuel = refuel
        fuelBean.mileage = mileage
        DbManager.getInstance().getFuelDao().insert(fuelBean)
        Log.d(_tag, "setFuelLiveData...${fuelBean.date}")
    }

    fun editFuelLiveData(refuel: String, mileage: String) {
        fuelLiveData.value =
            getNoMoreThanTwoDigits(refuel.toFloat().div(mileage.toFloat()).times(100))
        fuelBean.fuelC = fuelLiveData.value.toString()
        fuelBean.date = LocalDateTime.now().toString()
        fuelBean.refuel = refuel
        fuelBean.mileage = mileage
        val all = DbManager.getInstance().getFuelDao().getAll()
        Log.d(_tag, "setFuelLiveData...${all[1].fuelC}")
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