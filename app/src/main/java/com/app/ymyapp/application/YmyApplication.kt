package com.app.ymyapp.application

import android.app.Application

/**
 * @author:create by ys
 * 时间:2021/5/24 16
 * 邮箱 894417048@qq.com
 */
class YmyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: YmyApplication? = null
    }
}