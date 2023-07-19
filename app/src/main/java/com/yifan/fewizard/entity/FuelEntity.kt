package com.yifan.fewizard.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FuelEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    var fuelC: String? = null

    var date: String? = null

    var mileage: String? = null

    var refuel: String? = null

    var price: String? = null

}