package com.sketch.mandoo.rightnow.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sketch.mandoo.rightnow.R
import com.sketch.mandoo.rightnow.databinding.ItemBusBinding
import com.sketch.mandoo.rightnow.network.NetworkObject
import com.sketch.mandoo.rightnow.utils.log


class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var busList: List<NetworkObject.BusListModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemBusBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_bus,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(busList[position])
    }

    override fun getItemCount(): Int {
        return if(::busList.isInitialized) busList.size else 0
    }

    fun updateBusList(busList: List<NetworkObject.BusListModel>) {
        this.busList = busList
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemBusBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = MainViewModel()

        fun bind(bus: NetworkObject.BusListModel) {
            viewModel.bind(bus)
            binding.viewModel = viewModel
        }
    }
}