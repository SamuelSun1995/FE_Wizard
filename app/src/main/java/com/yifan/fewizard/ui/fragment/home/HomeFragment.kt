package com.yifan.fewizard.ui.fragment.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseFragment
import com.yifan.fewizard.bean.FuelBean
import com.yifan.fewizard.databinding.FragmentHomeBinding
import com.yifan.fewizard.ui.activity.main.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    private val _tag = "HomeFragment"
    override fun init() {
//        val bundle = arguments
//        try {
//            val lastFuel = bundle?.getFloat("refuel")?.div(bundle.getFloat("mileage"))?.times(100)
//            Log.d(_tag, "实际油耗:"+ lastFuel.toString())
//            mDataBinding.tvLastFuelFact.text = lastFuel.toString()
//        }catch (_:ArithmeticException){
//
//        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(_tag, "onResume")
        mDataBinding.fuelC = mViewModel.fuelC
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}