package com.sketch.mandoo.rightnow.network

import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit



object NetworkObject {
    data class BusListModel(
            val busRouteId: String,
            val busRouteNm: String,
            val firstBusTm: String,
            val lastBusTm: String,
            val routeType: String,
            val term: String
    )

    data class BusArriveInfoModel(
            val first_bus_time: String,
            val second_bus: String,
            val route_name: String,
            val route_index: Int
    )
}

