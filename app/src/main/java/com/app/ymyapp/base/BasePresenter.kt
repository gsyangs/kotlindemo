package com.app.ymyapp.base

import com.app.ymyapp.observer.BaseLifecycleObserver

/**
 * @author:create by ys
 * 时间:2021/5/25 16
 * 邮箱 894417048@qq.com
 */
open class BasePresenter<V : BaseView> : BaseLifecycleObserver{

    private var mView: V? = null

    fun getView(): V? {
        return mView
    }

    fun attachView(v: V) {
        mView = v
    }

    override fun onCreate() {
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        println("MMAIN_移除view")
        mView = null
    }


}