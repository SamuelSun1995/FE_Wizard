package com.yifan.fewizard.ui.fragment.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.yifan.fewizard.BR
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseFragment
import com.yifan.fewizard.databinding.FragmentHomeBinding
import com.yifan.fewizard.ui.activity.main.MainViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    private val _tag = "HomeFragment"
    override fun init() {
        mViewModel.getFuelLiveData().observe(this) { s: String ->
            Log.d(_tag, "init: fuelLiveData:$s")
        }
        //设置数据
        mDataBinding.setVariable(BR.mainViewModel, mViewModel)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
}