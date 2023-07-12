package com.yifan.fewizard.ui.fragment.home

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseFragment
import com.yifan.fewizard.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun init() {
//        handleEventDelay({
//            findNavController().navigate(R.id.action_homeFragment_to_mineFragment)
//        }, 5000)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }
}