package com.app.ymyapp.mvp.contract

import com.app.ymyapp.base.BasePresenter
import com.app.ymyapp.base.BaseView
import com.app.ymyapp.bean.ImageBo
import com.app.ymyapp.bean.InfosBo

/**
 * @author:create by ys
 * 时间:2021/5/25 15
 * 邮箱 894417048@qq.com
 */
interface MainContract {

    interface IMainView : BaseView{
        fun getImages(imageBos:List<ImageBo>)

        fun getWangYiNews(infosBos:List<InfosBo>)
    }

    abstract class IMainPresenter : BasePresenter<IMainView>() {
        abstract fun getImages()
        abstract fun getWangYiNews()
    }


}