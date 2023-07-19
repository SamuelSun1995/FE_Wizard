package com.yifan.fewizard.base

import android.content.res.Resources
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yifan.fewizard.R
import com.yifan.fewizard.config.App
import com.yifan.fewizard.config.LoadState

abstract class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    var mResources: Resources? = null

    /**
     * 加载状态
     */
    var loadState: MutableLiveData<LoadState> = MutableLiveData<LoadState>()

    var errorMsg: MutableLiveData<String> = MutableLiveData<String>(getResources()?.getString(R.string.load_error))

    /**
     * 重新加载数据。没有网络，点击重试时回调
     */
    open fun reloadData() {}

    open fun getResources(): Resources? {
        if (mResources == null) {
            mResources = App.mContext.resources
        }
        return mResources
    }
}