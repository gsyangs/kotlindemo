package com.app.ymyapp.mvp.model.interfaces

import android.content.Context
import com.app.ymyapp.mvp.listener.MainCompleteListener

/**
 * @author:create by ys
 * 时间:2021/5/25 17
 * 邮箱 894417048@qq.com
 */
interface IMainModel {
    fun getImages(
        listener: MainCompleteListener,
        context: Context
    )

    fun getWangYiNews(
        listener: MainCompleteListener,
        context: Context
    )
}