package com.sketch.mandoo.rightnow.network

import com.sketch.mandoo.rightnow.utils.BASE_URL
import com.sketch.mandoo.rightnow.utils.TOKEN
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun restAPI(retrofit: Retrofit): NetworkAPI {
        return retrofit.create(NetworkAPI::class.java)
    }

    private val httpClient = OkHttpClient.Builder()
            .addNetworkInterceptor { chain: Interceptor.Chain -> chain.proceed(chain.request().newBuilder().addHeader("token", TOKEN).build()) }!!


    @Provides
    @Reusable
    @JvmStatic
    internal fun retrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient.build())
                .build()
    }
}