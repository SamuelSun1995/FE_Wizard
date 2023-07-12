package com.yifan.fewizard.base

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.yifan.fewizard.R
import com.yifan.fewizard.config.LoadState
import com.yifan.fewizard.databinding.FragmentBaseBinding
import com.yifan.fewizard.databinding.ViewLoadErrorBinding
import com.yifan.fewizard.databinding.ViewLoadingBinding
import com.yifan.fewizard.databinding.ViewNoDataBinding
import com.yifan.fewizard.databinding.ViewNoNetworkBinding

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    lateinit var mDataBinding: DB

    lateinit var mViewModel: VM

    /**
     * lateinit的作用：延迟初始化
     * 具体来讲，这个关键字告诉编译器，我无法声明的时候就初始化，但是我保证我在使用前一定会初始化，你就别给我检查了。
     */
    private lateinit var mFragmentBaseBinding: FragmentBaseBinding

    private lateinit var mViewLoadingBinding: ViewLoadingBinding

    private lateinit var mViewNoNetworkBinding: ViewNoNetworkBinding

    private lateinit var mViewNoDataBinding: ViewNoDataBinding

    private lateinit var mViewLoadErrorBinding: ViewLoadErrorBinding

    protected var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var args = arguments
        if (args != null) {
            handleArguments(args)
        }
        initViewModel()

        // databinding 拥有生命周期感知
//        mDataBinding.lifecycleOwner = this

        // ViewModel订阅生命周期事件
        if (mViewModel != null) {
            lifecycle.addObserver(mViewModel)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentBaseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        mDataBinding = DataBindingUtil.inflate(inflater,getLayoutResId(),mFragmentBaseBinding.flContentContainer, true)
        // databinding 拥有生命周期感知
        mDataBinding.lifecycleOwner = this
        initLoadState()

        init()
        return mFragmentBaseBinding.root
    }

    /**
     * 有问题，需要的时候再看
     */
    open fun initLoadState() {
        if (mViewModel != null && isSupportLoad()) {
            mViewModel.loadState.observe(viewLifecycleOwner, this::switchLoadView)
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
                        mFragmentBaseBinding.flContentContainer,
                        false
                    )
                    mFragmentBaseBinding.flContentContainer.addView(mViewLoadingBinding.root)
                }
            }

            LoadState.NO_NETWORK -> {
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.view_no_network,
                        mFragmentBaseBinding.flContentContainer,
                        false
                    )
                    mViewNoNetworkBinding.viewModel = mViewModel
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewNoNetworkBinding.root)
            }

            LoadState.NO_DATA -> {
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.view_no_data,
                        mFragmentBaseBinding.flContentContainer,
                        false
                    )
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewNoDataBinding.root)
            }

            LoadState.ERROR -> {
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(
                        layoutInflater, R.layout.view_load_error,
                        mFragmentBaseBinding.flContentContainer, false
                    )
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewLoadErrorBinding.root)
            }

            else -> {}
        }
    }

    fun removeLoadView() {
        var childCount = mFragmentBaseBinding.flContentContainer.childCount

        if (childCount > 1) {
            mFragmentBaseBinding.flContentContainer.removeViews(1, childCount - 1)
        }
    }

    /**
     * 初始化
     */
    protected abstract fun init()

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
    protected abstract fun getLayoutResId(): Int

    /**
     * 处理参数
     *
     * @param args 参数容器
     */
    protected fun handleArguments(args: Bundle?) {}

    /**
     * 初始化ViewModel
     */
    protected abstract fun initViewModel()

    /**
     * 延时处理
     *
     * @param runnable
     * @param delayMillis
     */
    open fun handleEventDelay(runnable: Runnable?, delayMillis: Long) {
        if (mHandler == null) {
            mHandler = Handler()
        }
        if (runnable != null) {
            mHandler?.postDelayed(runnable, delayMillis)
        }
    }
}