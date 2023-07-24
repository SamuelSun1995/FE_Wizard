package com.yifan.fewizard.ui.activity.main

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.yifan.fewizard.adapter.FuelRecyclerAdapter
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

    private lateinit var mAdapter: FuelRecyclerAdapter

//    private val mFuelBean = FuelEntity()

    fun setListAdapter(adapter: FuelRecyclerAdapter) {
        mAdapter = adapter
    }

    fun initFuel(value: String) {
        fuelLiveData.value = value
    }

    fun setFuel(refuel: String, mileage: String) {
        val fuelEntity = FuelEntity()
        fuelLiveData.value =
            getNoMoreThanTwoDigits(refuel.toFloat().div(mileage.toFloat()).times(100))
        fuelEntity.fuelC = fuelLiveData.value.toString()
        fuelEntity.date = DateUtil.nowDateTime
        fuelEntity.refuel = refuel
        fuelEntity.mileage = mileage
        //存储数据库
        DbManager.getInstance().getFuelDao().insert(fuelEntity)
        //刷新recycler
        mAdapter.addItem(fuelEntity)
    }

    fun editFuelLiveData(refuel: String, mileage: String) {
        //获取到最新的数据
        val fuelEntity = DbManager.getInstance().getFuelDao().getLatest()
        fuelLiveData.value =
            getNoMoreThanTwoDigits(refuel.toFloat().div(mileage.toFloat()).times(100))
        fuelEntity.fuelC = fuelLiveData.value.toString()
        fuelEntity.date = DateUtil.nowDateTime
        fuelEntity.refuel = refuel
        fuelEntity.mileage = mileage
        //更新数据库
        DbManager.getInstance().getFuelDao().update(fuelEntity)
        //刷新recycler
        mAdapter.updateItem(fuelEntity)
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

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        //销毁时插入
//        DbManager.getInstance().getFuelDao().insert(mFuelBean)
    }
}