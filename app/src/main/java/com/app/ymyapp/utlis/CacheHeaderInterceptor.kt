package com.app.ymyapp.utlis

import com.app.ymyapp.application.YmyApplication
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * @author:create by ys
 * 时间:2021/5/25 18
 * 邮箱 894417048@qq.com
 */
class CacheHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // 设置缓存配置
        val cacheBuilder = CacheControl.Builder()
        cacheBuilder.maxAge(0, TimeUnit.SECONDS)
        cacheBuilder.maxStale(30, TimeUnit.DAYS)
        val cacheControl = cacheBuilder.build()

        // 获取到之前的Request
        var request = chain!!.request()
        // 判断请求方法，GET才缓存
        if (!NetworkUtil.isNetConnected()) {
            request = request.newBuilder()
                .cacheControl(cacheControl)
                .build()
        }
        val originalResponse = chain.proceed(request)
        // 根据网络状况，做不同的缓存策略
        return if (NetworkUtil.isNetConnected()) {
            val originalResponse = chain.proceed(request)
            val maxAge = 0 // read from cache
            originalResponse.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public ,max-age=$maxAge")
                .build()
        } else {
            val maxStale = 60 * 60 * 24 * 28 // 4周
            originalResponse.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
    }
}