package com.sketch.mandoo.rightnow.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sketch.mandoo.rightnow.main.listener.Listener

class MainViewModelFactory(val listener: Listener.SelectBusListener) : ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainListViewModel::class.java)) {
            return MainListViewModel(listener) as T
        }
        throw IllegalAccessException("Unknown Exception")
    }

}