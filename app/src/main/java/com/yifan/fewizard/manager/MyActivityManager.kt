package com.yifan.fewizard.manager

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * Kotlin 单例https://blog.csdn.net/qq_23025319/article/details/107061895
 */
class MyActivityManager {

    companion object {
        private var sInstance: MyActivityManager? = null

        @Synchronized
        fun getInstance(): MyActivityManager {
            if (sInstance == null) {
                sInstance = MyActivityManager()
            }
            return sInstance as MyActivityManager
        }
    }

    /**
     * 若引用当前Activity
     */
    private var sCurrentActivityWeakRef: WeakReference<Activity>? = null

    /**
     * 获取当前的Activity
     *
     * @return
     */
    fun getCurrentActivity(): Activity? {
        var currentActivity: Activity? = null
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef!!.get()
        }
        return currentActivity
    }

    /**
     * 保存Activity
     *
     * @param activity
     */
    fun setCurrentActivity(activity: Activity) {
        sCurrentActivityWeakRef = WeakReference(activity)
    }
}