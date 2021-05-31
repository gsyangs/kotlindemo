package com.app.ymyapp.observer

import com.app.ymyapp.utlis.ExceptionHandle


/**
 * @author:create by ys
 * 时间:2021/5/24 17
 * 邮箱 894417048@qq.com
 */
interface ObserverResponseListener<T> {

    /**
     * 响应成功
     * @param t
     */
    fun onNext(t: T)

    /**
     * 响应失败
     * @param e
     */
    fun onError(e: ExceptionHandle.ResponeThrowable?)
}