package com.app.ymyapp.mvp.model

import android.content.Context
import com.app.ymyapp.api.RtApiUtulis
import com.app.ymyapp.base.BaseModel
import com.app.ymyapp.bean.ImageBo
import com.app.ymyapp.bean.InfosBo
import com.app.ymyapp.mvp.listener.MainCompleteListener
import com.app.ymyapp.mvp.model.interfaces.IMainModel
import com.app.ymyapp.observer.ObserverResponseListener
import com.app.ymyapp.utlis.ExceptionHandle
import com.app.ymyapp.utlis.ToastUtil

/**
 * @author:create by ys
 * 时间:2021/5/24 16
 * 邮箱 894417048@qq.com
 */
class MainModel : BaseModel(),IMainModel{

    override fun getImages(listener: MainCompleteListener, context: Context) {

        var map = HashMap<String,Any>()
        map["page"] = "2"
        map["count"] = "20"

        subscribe(context, RtApiUtulis.apiService.getImages(map),
            object : ObserverResponseListener<List<ImageBo>> {
            override fun onNext(t: List<ImageBo>) {
                listener!!.getImages(t)
            }

            override fun onError(e: ExceptionHandle.ResponeThrowable?) {
                if (e != null) {
                    e.message?.let { ToastUtil.showLongToast(it) }
                }
            }
        }, isDialog = true, cancelable = true
        )

    }

    override fun getWangYiNews(listener: MainCompleteListener, context: Context) {

        var map = HashMap<String,Any>();
        map["page"] = "2"
        map["count"] = "20"
        subscribe(context,RtApiUtulis.apiService.getWangYiNews(map),
            object : ObserverResponseListener<List<InfosBo>>{
            override fun onNext(t: List<InfosBo>) {
            }

            override fun onError(e: ExceptionHandle.ResponeThrowable?) {
            }
        },isDialog = true, cancelable = true
        )
    }

}