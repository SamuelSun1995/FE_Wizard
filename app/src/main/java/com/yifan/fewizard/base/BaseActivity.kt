package com.yifan.fewizard.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.yifan.fewizard.R
import com.yifan.fewizard.config.LoadState
import com.yifan.fewizard.databinding.ActivityBaseBinding
import com.yifan.fewizard.databinding.ViewLoadErrorBinding
import com.yifan.fewizard.databinding.ViewLoadingBinding
import com.yifan.fewizard.databinding.ViewNoDataBinding
import com.yifan.fewizard.databinding.ViewNoNetworkBinding

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mDataBinding: DB

    lateinit var mViewModel: VM

    /**
     * lateinit的作用：延迟初始化
     * 具体来讲，这个关键字告诉编译器，我无法声明的时候就初始化，但是我保证我在使用前一定会初始化，你就别给我检查了。
     */
    private lateinit var mActivityBaseBinding: ActivityBaseBinding

    private lateinit var mViewLoadingBinding: ViewLoadingBinding

    private lateinit var mViewNoNetworkBinding: ViewNoNetworkBinding

    private lateinit var mViewNoDataBinding: ViewNoDataBinding

    private lateinit var mViewLoadErrorBinding: ViewLoadErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            handleArguments(savedInstanceState)
        }
        handleIntent(intent)
        mActivityBaseBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_base)
        mDataBinding = DataBindingUtil.inflate<DB>(
            layoutInflater,
            getLayoutResId(),
            mActivityBaseBinding.flContentContainer,
            true
        )
        initViewModel()
        bindViewModel()
//        mDataBinding.setLifecycleOwner(this)
        // databinding 拥有生命周期感知
        mDataBinding.lifecycleOwner = this

        initLoadState()
        init()

        // ViewModel订阅生命周期事件
        if (mViewModel != null) {
            lifecycle.addObserver(mViewModel)
        }
    }

    /**
     * 初始化
     */
    protected abstract fun init()

    open fun initLoadState() {
        if (mViewModel != null && isSupportLoad()) {
            mViewModel.loadState.observe(this, this::switchLoadView)
        }
    }

    fun switchLoadView(loadState: LoadState) {
        removeLoadView()
        when (loadState) {
            LoadState.LOADING -> {
                if (mViewLoadingBinding == null) {
                    mViewLoadingBinding = DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.view_loading,
                        mActivityBaseBinding.flContentContainer,
                        false
                    )
                    mActivityBaseBinding.flContentContainer.addView(mViewLoadingBinding.root)
                }
            }

            LoadState.NO_NETWORK -> {
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.view_no_network,
                        mActivityBaseBinding.flContentContainer,
                        false
                    )
                    mViewNoNetworkBinding.viewModel = mViewModel
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoNetworkBinding.root)
            }

            LoadState.NO_DATA -> {
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.view_no_data,
                        mActivityBaseBinding.flContentContainer,
                        false
                    )
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoDataBinding.root)
            }

            LoadState.ERROR -> {
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(
                        layoutInflater, R.layout.view_load_error,
                        mActivityBaseBinding.flContentContainer, false
                    )
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadErrorBinding.root)
            }

            else -> {}
        }
    }

    fun removeLoadView() {
        var childCount = mActivityBaseBinding.flContentContainer.childCount

        if (childCount > 1) {
            mActivityBaseBinding.flContentContainer.removeViews(1, childCount - 1)
        }
    }


    /**
     * 是否支持页面加载。默认不支持
     *
     * @return true表示支持，false表示不支持
     */
    protected open fun isSupportLoad(): Boolean {
        return false
    }

    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    abstract fun getLayoutResId(): Int

    abstract fun initViewModel()

    abstract fun bindViewModel()

    /**
     * 处理参数
     *
     * @param args 参数容器
     */
    protected open fun handleArguments(args: Bundle?) {}

    /**
     * 处理参数
     *
     * @param intent 参数容器
     */
    protected open fun handleIntent(intent: Intent?) {}
}