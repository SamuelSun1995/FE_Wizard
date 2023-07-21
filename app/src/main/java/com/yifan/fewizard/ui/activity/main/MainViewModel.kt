package com.yifan.fewizard.ui.activity.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yifan.fewizard.base.BaseViewModel
import com.yifan.fewizard.database.entity.FuelEntity
import com.yifan.fewizard.manager.DbManager
import com.yifan.fewizard.util.DateUtil
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime

class MainViewModel : BaseViewModel() {

    private val _tag = "MainViewModel"

    private var fuelLiveData = MutableLiveData<String>()

    private val mFuelBean = FuelEntity()

    fun setFuelLiveData(refuel: String, mileage: String) {
        oldFuelSaveDb()
        fuelLiveData.value =
            getNoMoreThanTwoDigits(refuel.toFloat().div(mileage.toFloat()).times(100))
        mFuelBean.fuelC = fuelLiveData.value.toString()
        mFuelBean.date = DateUtil.nowDate
        mFuelBean.refuel = refuel
        mFuelBean.mileage = mileage
        Log.d(_tag, "setFuelLiveData...${mFuelBean.date}")
    }

    fun editFuelLiveData(refuel: String, mileage: String) {
        fuelLiveData.value =
            getNoMoreThanTwoDigits(refuel.toFloat().div(mileage.toFloat()).times(100))
        mFuelBean.fuelC = fuelLiveData.value.toString()
        mFuelBean.date = DateUtil.nowDate
        mFuelBean.refuel = refuel
        mFuelBean.mileage = mileage
        val all = DbManager.getInstance().getFuelDao().getAll()
        Log.d(_tag, "setFuelLiveData...${all[1].fuelC}")
    }

    private fun oldFuelSaveDb() {
        if (mFuelBean.date == null) {
            return
        }
        DbManager.getInstance().getFuelDao().insert(mFuelBean)
        Log.d(_tag, "oldFuelSaveDb...")
    }

    fun getFuelLiveData(): LiveData<String> {
        return fuelLiveData
    }

    fun getAllFuel(): List<FuelEntity> {
        return DbManager.getInstance().getFuelDao().getAll()
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