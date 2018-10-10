package com.sketch.mandoo.rightnow.network

import com.sketch.mandoo.rightnow.utils.GET_BUS_ARRIVE_INFO
import com.sketch.mandoo.rightnow.utils.GET_BUS_LIST
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkAPI {
    @GET(GET_BUS_LIST)
    fun getBusList(@Query("number") number: String): Observable<List<NetworkObject.BusListModel>>

    @GET(GET_BUS_ARRIVE_INFO)
    fun getBUsArriveList(@Query("busNumber") number:String, @Query("busStation") station:String) : Observable<List<NetworkObject.BusArriveInfoModel>>
}