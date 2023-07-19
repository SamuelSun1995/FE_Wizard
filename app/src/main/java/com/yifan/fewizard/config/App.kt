package com.yifan.fewizard.config

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

class App : Application() {
    //静态变量
    companion object {
        lateinit var mContext: Context
    }

    private var mFirstOpen = false

    override fun onCreate() {
        super.onCreate()
        mContext = baseContext
        mFirstOpen = true
        initActivityManager()
        init()
    }

    private fun init() {

    }

//    companion object {
//        @JvmStatic
//        fun getContext(): Context? {
//            return App().mContext
//        }
//    }

    private fun initActivityManager() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }
        })
    }

}