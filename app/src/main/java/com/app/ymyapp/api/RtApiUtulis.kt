package com.app.ymyapp.api

/**
 * @author:create by ys
 * 时间:2021/5/24 16
 * 邮箱 894417048@qq.com
 */
class RtApiUtulis {

    /**
     * 静态 懒加载单例
     */
    companion object{
        val BASE_URL = "https://api.apiopen.top/"
        val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            BaseApi().getRetrofit(BASE_URL).create(ApiService::class.java)
        }
    }

}