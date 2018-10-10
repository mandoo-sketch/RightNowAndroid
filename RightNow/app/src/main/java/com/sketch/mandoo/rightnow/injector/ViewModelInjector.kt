package com.sketch.mandoo.rightnow.injector

import com.sketch.mandoo.rightnow.network.NetworkModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun serviceModule(serviceModule:NetworkModule): Builder
    }
}