package com.yifan.fewizard.ui.activity.splash

import android.Manifest
import android.graphics.Typeface
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseActivity
import com.yifan.fewizard.databinding.ActivitySplashBinding
import java.util.function.Consumer

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private val FONTS = "fonts/yizhi.ttf"
    private val TAG = "SplashActivity"

    private val permsGrouping = arrayOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS
    )

    /**
     * 设置沉浸式状态栏
     */
    override fun isNoActionBar(): Boolean {
        showT("setNoActionBar...")
        return true
    }

    override fun init() {
        //设置字体
        setFont()
        //跳转至主页
        startToMain()
    }

    /**
     * 跳转至主页
     */
    private fun startToMain() {
        initPermissions()
    }

    /**
     * 设置字体
     */
    private fun setFont() {
        val typeface = Typeface.createFromAsset(assets, FONTS)
        mDataBinding.appnameTxt.typeface = typeface
        mDataBinding.sloganTxt.typeface = typeface
        mDataBinding.bottomAppnameTxt.typeface = typeface
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
    }

    private fun initPermissions() {
        val rxPermissions = RxPermissions(this)
        //星号的作用是，在数组对象前加*号可以将数组展开，以方便传值。
        rxPermissions.requestEach(*permsGrouping)
            // 这边Kotlin语法有时间再看下
            .subscribe {
                Consumer<Permission> { t ->
                    if (t.granted) {
                        // 用户已经同意该权限
                        Log.d(TAG, t.name + " is granted.");
                    } else if (t.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                        Log.d(TAG, t.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，而且选中『不再询问』
                        Log.d(TAG, t.name + " is denied.");
                    }
                }
            }
    }
}

