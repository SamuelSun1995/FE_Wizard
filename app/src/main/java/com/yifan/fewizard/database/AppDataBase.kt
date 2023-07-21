package com.yifan.fewizard.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yifan.fewizard.database.dao.FuelDao
import com.yifan.fewizard.database.entity.FuelEntity

/**
 * 注解指定了database的表映射实体数据以及版本等信息(后面会详细讲解版本升级)
 */
@Database(entities = [FuelEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getFuelDao(): FuelDao

}