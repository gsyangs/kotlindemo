package com.app.ymyapp.api

import android.text.TextUtils
import android.util.Log
import com.app.ymyapp.application.YmyApplication
import com.app.ymyapp.utlis.CacheHeaderInterceptor
import com.app.ymyapp.utlis.NetworkUtil
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author:create by ys
 * 时间:2021/5/24 16
 * 邮箱 894417048@qq.com
 */
class BaseApi {

    //申明静态常量
    companion object{
        //读超时长，单位：毫秒
        val READ_TIME_OUT = 7676

        //连接时长，单位：毫秒
        val CONNECT_TIME_OUT = 7676
    }

    fun getRetrofit(baseUrl:String?):Retrofit{
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //缓存
        val cacheFile =
            File(YmyApplication.instance?.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 100) //100Mb

        //增加头部信息
        val headerInterceptor =
            Interceptor { chain: Interceptor.Chain ->
                val build = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json") //设置允许请求json数据
                    .build()
                chain.proceed(build)
            }

        //创建一个OkHttpClient并设置超时时间
        val client = OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            .connectTimeout(
                CONNECT_TIME_OUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .addInterceptor(CacheHeaderInterceptor())
            .addNetworkInterceptor(CacheHeaderInterceptor())
            .addInterceptor(headerInterceptor)
            .addInterceptor(logInterceptor)
            .cache(cache)
            .build()


        // 日志显示级别
        val level = HttpLoggingInterceptor.Level.BASIC
        //新建log拦截器
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String ->
                Log.e(
                    "test",
                    "OkHttp====Message:$message"
                )
            })
        loggingInterceptor.level = level
        //OkHttp进行添加拦截器loggingInterceptor
        client.newBuilder().addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create()) //请求结果转换为基本类型，一般为String
            .addConverterFactory(GsonConverterFactory.create()) //请求的结果转为实体类
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }
}