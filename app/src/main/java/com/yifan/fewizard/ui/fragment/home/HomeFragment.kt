package com.yifan.fewizard.ui.fragment.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yifan.fewizard.BR
import com.yifan.fewizard.R
import com.yifan.fewizard.adapter.FuelRecyclerAdapter
import com.yifan.fewizard.base.BaseFragment
import com.yifan.fewizard.database.entity.FuelEntity
import com.yifan.fewizard.databinding.FragmentHomeBinding
import com.yifan.fewizard.ui.activity.main.MainViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    private val _tag = "HomeFragment"

    private lateinit var mAdapter: FuelRecyclerAdapter
    override fun init() {
        mViewModel.getFuelLiveData().observe(this) { s: String ->
            Log.d(_tag, "init: fuelLiveData:${s.toString()}")
        }
        //获取服务器的数据
        val list = getDataFromDbAndServer()
        //设置数据绑定
        mDataBinding.setVariable(BR.mainViewModel, mViewModel)
        //长按修改最新油耗记录
        editFuelC()
        mDataBinding.recylerFuel.layoutManager = LinearLayoutManager(activity)
        mAdapter = FuelRecyclerAdapter(list as ArrayList<FuelEntity>)
        mDataBinding.recylerFuel.adapter = mAdapter
        mViewModel.setListAdapter(mAdapter)
        mViewModel.initFuel(list[0].fuelC.toString())
    }

    private fun editFuelC() {
        mDataBinding.containerFuel.setOnLongClickListener {
            //弹出对话框
            val builder = AlertDialog.Builder(context).setTitle("记录油耗").setIcon(R.mipmap.fuel)
            val diaLayout =
                layoutInflater.inflate(R.layout.dialog_add_fuel_layout, null) as LinearLayout
            builder.setView(diaLayout)
            builder.setPositiveButton("确定") { _: DialogInterface, _: Int ->
                mViewModel.editFuelLiveData(
                    diaLayout.findViewById<EditText>(R.id.ed_refuel).text.trim().toString(),
                    diaLayout.findViewById<EditText>(R.id.ed_mileage).text.trim()
                        .toString()
                )

                Log.d(_tag, " fuelC:" + mViewModel.getFuelLiveData().value)
            }
            builder.setNegativeButton("取消") { _: DialogInterface, _: Int ->
            }
            builder.create().show()
            false
        }
    }

    private fun getDataFromDbAndServer(): List<FuelEntity> {
        return mViewModel.getAllFuel()
    }

    private fun getDataFromDb(): List<FuelEntity> {
        val list = ArrayList<FuelEntity>()
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