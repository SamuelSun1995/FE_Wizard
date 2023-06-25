package com.yifan.fewizard.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.yifan.fewizard.R
import com.yifan.fewizard.databinding.ActivityBaseBinding

open class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

//    private var mActivityBaseBinding: ActivityBaseBinding? = null

    /**
     * lateinit的作用：延迟初始化
     * 具体来讲，这个关键字告诉编译器，我无法声明的时候就初始化，但是我保证我在使用前一定会初始化，你就别给我检查了。
     */
    private lateinit var mActivityBaseBinding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBaseBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_base)
    }
}