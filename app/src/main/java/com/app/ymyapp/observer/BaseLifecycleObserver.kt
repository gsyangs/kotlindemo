package com.app.ymyapp.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author:create by ys
 * 时间:2021/5/25 16
 * 邮箱 894417048@qq.com
 */
interface BaseLifecycleObserver : LifecycleObserver{

    //创建时
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    //开始启动的时候
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    //界面可见时候
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    //界面停止将要切换
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    //界面完全隐藏时候
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()

    //结束时候
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()

}