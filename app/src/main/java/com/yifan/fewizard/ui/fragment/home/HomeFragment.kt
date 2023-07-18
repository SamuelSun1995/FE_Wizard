package com.yifan.fewizard.ui.fragment.home

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.yifan.fewizard.BR
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseFragment
import com.yifan.fewizard.bean.FuelBean
import com.yifan.fewizard.databinding.FragmentHomeBinding
import com.yifan.fewizard.ui.activity.main.MainViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    private val _tag = "HomeFragment"
    override fun init() {
        mViewModel.getFuelLiveData().observe(this) { s: String ->
            Log.d(_tag, "init: fuelLiveData:$s")
        }
        //获取服务器的数据
        getDataFromServer()
        //设置数据绑定
        mDataBinding.setVariable(BR.mainViewModel, mViewModel)
        //长按修改最新油耗记录
        editFuelC()
    }

    private fun editFuelC() {
//        mDataBinding.containerFuel.setOnLongClickListener {
//
//        }
    }

    private fun getDataFromServer(): List<FuelBean> {
        val list = ArrayList<FuelBean>()
        return list
    }

    private fun getDataFromDb(): List<FuelBean> {
        val list = ArrayList<FuelBean>()
        return list
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