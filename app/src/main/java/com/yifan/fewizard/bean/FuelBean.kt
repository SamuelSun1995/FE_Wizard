package com.yifan.fewizard.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.yifan.fewizard.BR

class FuelBean : BaseObservable() {

    var fuelC = "0.0"

    var date: String? = null

    var mileage: String? = null

    var refuel: String? = null
}