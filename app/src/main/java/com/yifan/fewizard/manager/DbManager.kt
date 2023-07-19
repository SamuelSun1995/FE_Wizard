package com.yifan.fewizard.manager

import androidx.room.Room
import com.yifan.fewizard.config.App
import com.yifan.fewizard.database.AppDataBase

class DbManager {

    companion object {
        private var sInstance: AppDataBase? = null
        val DB_NAME = "fuel_db"

        @Synchronized
        fun getInstance(): AppDataBase {
            if (sInstance == null) {
                sInstance =
                    Room.databaseBuilder(App.mContext, AppDataBase::class.java, DB_NAME)
                        //下面注释表示允许主线程进行数据库操作，但是不推荐这样做。
                        //我这里是为了Demo展示，稍后会结束和LiveData和RxJava的使用
                        .allowMainThreadQueries()
                        .build()
            }
            return sInstance as AppDataBase
        }
    }
}