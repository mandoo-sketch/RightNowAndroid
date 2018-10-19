package com.sketch.mandoo.rightnow.main

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.telephony.euicc.DownloadableSubscription
import android.util.Log
import android.view.View
import com.sketch.mandoo.rightnow.R
import com.sketch.mandoo.rightnow.base.BaseViewModel
import com.sketch.mandoo.rightnow.main.listener.Listener
import com.sketch.mandoo.rightnow.network.NetworkAPI
import com.sketch.mandoo.rightnow.network.NetworkObject
import com.sketch.mandoo.rightnow.route.RouteActivity
import com.sketch.mandoo.rightnow.utils.log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainListViewModel(val listener: Listener.SelectBusListener) :BaseViewModel(){

    @Inject
    lateinit var serviceAPI : NetworkAPI

    private lateinit var subscription: Disposable

    private val storageNumber = MutableLiveData<String>()

    fun setBusNumber(number: String) {
        storageNumber.value = number
    }

    val mainAdapter = MainAdapter(listener)

    val errorMessage:MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadBusList(storageNumber.value!!) }

    val searchClickListener = View.OnClickListener {
        loadBusList(storageNumber.value!!)
    }

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private fun loadBusList(number:String) {
        subscription = serviceAPI.getBusList(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{onRetrieveBusListStart()}
                .doOnTerminate{onRetrieveBusListFinish()}
                .subscribe(
                        {result -> onRetrieveBusListSuccess(result)},
                        {err -> onRetrieveBusListError(err)}
                )
    }

    private fun onRetrieveBusListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveBusListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveBusListSuccess(busList: List<NetworkObject.BusListModel>){
//        log(busList)
        mainAdapter.updateBusList(busList)
    }

    private fun onRetrieveBusListError(err: Throwable) {
        var message = err.message
        message += R.string.post_error.toString()
        errorMessage.value = message
    }


}