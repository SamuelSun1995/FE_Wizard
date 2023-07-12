package com.yifan.fewizard.ui.activity.main

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseActivity
import com.yifan.fewizard.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun init() {
        val navController = findNavController(R.id.nav_host_fragment_container)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_mine))
        setupActionBarWithNavController(navController, appBarConfiguration)
        setupWithNavController(mDataBinding.bottomNav, navController)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}