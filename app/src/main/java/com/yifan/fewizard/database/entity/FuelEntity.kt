package com.yifan.fewizard.database.entity

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

    override fun toString(): String {
        return "id:$id， fuelC：$fuelC, date:$date, mileage:$mileage, refuel:$refuel, price:$price"
    }

}