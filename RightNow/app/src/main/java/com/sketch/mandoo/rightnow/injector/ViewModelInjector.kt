package com.sketch.mandoo.rightnow.injector

import com.sketch.mandoo.rightnow.main.MainListViewModel
import com.sketch.mandoo.rightnow.main.MainViewModel
import com.sketch.mandoo.rightnow.network.NetworkModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(mainListViewModel: MainListViewModel)

    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule:NetworkModule): Builder
    }
}