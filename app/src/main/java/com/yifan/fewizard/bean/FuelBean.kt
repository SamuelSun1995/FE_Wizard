package com.yifan.fewizard.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.yifan.fewizard.BR

class FuelBean : BaseObservable() {

    @Bindable
    var fuelC = "0.0"
        set(value) {
            field = value
            notifyPropertyChanged(BR.fuelC)
        }

    @Bindable
    var mileage: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mileage)
        }

    @Bindable
    var refuel: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.refuel)
        }
}