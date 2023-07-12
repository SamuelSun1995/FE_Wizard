package com.yifan.fewizard.ui.fragment.home

import androidx.lifecycle.ViewModelProvider
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseFragment
import com.yifan.fewizard.databinding.FragmentHomeBinding
import com.yifan.fewizard.ui.fragment.mine.MineViewModel

class MineFragment:BaseFragment<FragmentHomeBinding, MineViewModel>() {
    override fun init() {
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_mine
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(this)[MineViewModel::class.java]
    }
}