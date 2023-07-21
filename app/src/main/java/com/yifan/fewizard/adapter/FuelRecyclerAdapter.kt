package com.yifan.fewizard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yifan.fewizard.R
import com.yifan.fewizard.database.entity.FuelEntity

class FuelRecyclerAdapter(var list: List<FuelEntity>) :
    RecyclerView.Adapter<FuelRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var date = view.findViewById<TextView>(R.id.tv_refuel_date)
        var quote = view.findViewById<TextView>(R.id.tv_refuel_quote)
        var fuelC = view.findViewById<TextView>(R.id.tv_refuel_fuel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FuelRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_fuel, parent, false)
        var viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: FuelRecyclerAdapter.ViewHolder, position: Int) {
        val fuel = list[position]
        holder.date.text = fuel.date
        holder.quote.text = "-"
        holder.fuelC.text = fuel.fuelC
    }

    override fun getItemCount(): Int {
        return list.size
    }
}