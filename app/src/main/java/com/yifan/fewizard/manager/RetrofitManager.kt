package com.yifan.fewizard.manager

import com.yifan.fewizard.constant.SystemConst
import com.yifan.fewizard.interceptor.LogInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager {

    companion object {
        private var sInstance: RetrofitManager? = null

        @Synchronized
        fun getInstance(): RetrofitManager {
            if (sInstance == null) {
                sInstance = RetrofitManager()
            }
            return sInstance as RetrofitManager
        }
    }

    fun getDefaultRetrofit(): Retrofit? {
        return Retrofit.Builder()
            .client(initOkHttp())
            .baseUrl(SystemConst.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getSpecialUrlRetrofit(baseUrl: String?): Retrofit? {
        return Retrofit.Builder()
            .client(initOkHttp())
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * 初始化必要对象和参数
     */
    private fun initOkHttp(): OkHttpClient? {
        return OkHttpClient().newBuilder()
            .readTimeout(SystemConst.DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(SystemConst.DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(SystemConst.DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(LogInterceptor())
            .retryOnConnectionFailure(true)
            .protocols(listOf<Protocol>(Protocol.HTTP_1_1))
            .build()
    }
}