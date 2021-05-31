package com.app.ymyapp.base

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.app.ymyapp.observer.ObserverResponseListener
import com.app.ymyapp.observer.ProgressObserver
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author:create by ys
 * 时间:2021/5/24 17
 * 邮箱 894417048@qq.com
 */
open class BaseModel {

    /**
     * 封装线程管理和订阅的过程
     * flag  是否添加progressdialog
     */
    @SuppressLint("CheckResult")
    fun <T> subscribe(
        context: Context,
        observable: Observable<*>,
        listener: ObserverResponseListener<T>,
        isDialog: Boolean,
        cancelable: Boolean
    ) {
        observable.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                var observer: Observer<T> = ProgressObserver(context, listener, isDialog, cancelable)
                //绑定生命周期
                (context as AppCompatActivity).lifecycle.addObserver((observer as LifecycleObserver))
            }
    }

}