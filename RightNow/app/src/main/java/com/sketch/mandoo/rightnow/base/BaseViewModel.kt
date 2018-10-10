package com.sketch.mandoo.rightnow.base

import android.arch.lifecycle.ViewModel
import com.sketch.mandoo.rightnow.injector.DaggerViewModelInjector
import com.sketch.mandoo.rightnow.injector.ViewModelInjector
import com.sketch.mandoo.rightnow.main.MainListViewModel
import com.sketch.mandoo.rightnow.main.MainViewModel
import com.sketch.mandoo.rightnow.network.NetworkModule

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is MainListViewModel -> injector.inject(this)
            is MainViewModel -> injector.inject(this)
        }
    }
}