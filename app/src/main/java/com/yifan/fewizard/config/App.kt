package com.yifan.fewizard.config

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

class App : Application() {

    @JvmField
    var mContext: Context? = null
    private var mFirstOpen = false

    override fun onCreate() {
        super.onCreate()
        mContext = this
        mFirstOpen = true
        initActivityManager()
        init()
    }

    private fun init() {

    }

    companion object {
        @JvmStatic
        fun getContext(): Context? {
            return App().mContext
        }
    }

    private fun initActivityManager() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                TODO("Not yet implemented")
            }

            override fun onActivityStarted(activity: Activity) {
                TODO("Not yet implemented")
            }

            override fun onActivityResumed(activity: Activity) {
                TODO("Not yet implemented")
            }

            override fun onActivityPaused(activity: Activity) {
                TODO("Not yet implemented")
            }

            override fun onActivityStopped(activity: Activity) {
                TODO("Not yet implemented")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                TODO("Not yet implemented")
            }

            override fun onActivityDestroyed(activity: Activity) {
                TODO("Not yet implemented")
            }
        })
    }

}