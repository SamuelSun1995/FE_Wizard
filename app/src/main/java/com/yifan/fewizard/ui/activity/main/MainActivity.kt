package com.yifan.fewizard.ui.activity.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.yifan.fewizard.R
import com.yifan.fewizard.base.BaseActivity
import com.yifan.fewizard.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val _tag = "MainActivity"
    override fun init() {
        onClickInit()
    }

    private fun onClickInit() {
        mDataBinding.navHome.setOnClickListener {
            try {
                findNavController(R.id.nav_host_fragment_container).navigate(R.id.nav_home)
            } catch (e: IllegalArgumentException) {
                Log.e(_tag, "findNavController home IllegalArgumentException...")
            }
        }

        mDataBinding.navMine.setOnClickListener {
            try {
                findNavController(R.id.nav_host_fragment_container).navigate(R.id.nav_mine)
            } catch (e: IllegalArgumentException) {
                Log.e(_tag, "findNavController mine IllegalArgumentException...")
            }
        }

        mDataBinding.add.setOnClickListener {
            //弹出对话框
            val builder = AlertDialog.Builder(this).setTitle("记录油耗").setIcon(R.mipmap.fuel)
            val diaLayout =
                layoutInflater.inflate(R.layout.dialog_add_fuel_layout, null) as LinearLayout
            builder.setView(diaLayout)
            builder.setPositiveButton("确定") { _: DialogInterface, _: Int ->
                mViewModel.setFuel(
                    diaLayout.findViewById<EditText>(R.id.ed_refuel).text.trim().toString(),
                    diaLayout.findViewById<EditText>(R.id.ed_mileage).text.trim()
                        .toString()
                )
                Log.d(_tag, " fuelC:" + mViewModel.getFuelLiveData().value)
            }
            builder.setNegativeButton("取消") { _: DialogInterface, _: Int ->
            }
            builder.create().show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //把最新的油耗记录存起来

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}