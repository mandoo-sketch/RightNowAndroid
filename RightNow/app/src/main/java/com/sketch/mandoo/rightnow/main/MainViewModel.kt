package com.sketch.mandoo.rightnow.main

import android.arch.lifecycle.MutableLiveData
import com.sketch.mandoo.rightnow.base.BaseViewModel
import com.sketch.mandoo.rightnow.network.NetworkObject


class MainViewModel :BaseViewModel(){
    private val busId = MutableLiveData<String>()
    private val busNumber = MutableLiveData<String>()
    private val busFirstTime = MutableLiveData<String>()
    private val busLastTime = MutableLiveData<String>()
    private val busTerm = MutableLiveData<String>()
    private val busRouteType = MutableLiveData<String>()

    fun bind(serviceModel: NetworkObject.BusListModel) {
        busId.value = serviceModel.busRouteId
        busNumber.value = serviceModel.busRouteNm
        busFirstTime.value = serviceModel.firstBusTm
        busLastTime.value = serviceModel.lastBusTm
        busTerm.value = serviceModel.term
        busRouteType.value = serviceModel.routeType
    }

    fun getBusId() = busId
    fun getBusNumber() = busNumber
    fun getBusFirstTime() = busFirstTime
    fun getBusLastTime() = busLastTime
    fun getBusTerm() = busTerm
    fun getBusRouteType() = busRouteType
}
