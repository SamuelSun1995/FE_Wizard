package com.yifan.fewizard.ui.fragment.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseFragment
import com.yifan.fewizard.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val _tag = "HomeFragment"
    override fun init() {
        val bundle = arguments
        try {
            val lastFuel = bundle?.getFloat("refuel")?.div(bundle.getFloat("mileage"))?.times(100)
            Log.d(_tag, "实际油耗:"+ lastFuel.toString())
            mDataBinding.tvLastFuelFact.text = lastFuel.toString()
        }catch (_:ArithmeticException){

        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }
}