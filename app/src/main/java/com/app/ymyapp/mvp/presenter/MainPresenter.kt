package com.app.ymyapp.mvp.presenter

import android.content.Context
import com.app.ymyapp.bean.ImageBo
import com.app.ymyapp.bean.InfosBo
import com.app.ymyapp.mvp.contract.MainContract
import com.app.ymyapp.mvp.listener.MainCompleteListener
import com.app.ymyapp.mvp.model.MainModel

/**
 * @author:create by ys
 * 时间:2021/5/25 16
 * 邮箱 894417048@qq.com
 */
class MainPresenter :MainContract.IMainPresenter,MainCompleteListener{

    var context :Context
    var mainModel:MainModel

    constructor(context: Context){
        this.context = context
        mainModel = MainModel()
    }

    override fun getImages() {
        mainModel.getImages(this,context)
    }

    override fun getWangYiNews() {
        mainModel.getWangYiNews(this,context)
    }

    override fun getImages(imageBos: List<ImageBo>) {
        if (imageBos.isNotEmpty()) {
            for (imageBo in imageBos) {
               print("MAIN_IMAGE_URL: " + imageBo.img)
            }
        }
    }

    override fun getWangYiNews(infosBos: List<InfosBo>) {
        if (infosBos.isNotEmpty()){
            for (infoBo in infosBos){
                print("MAIN_IMAGE_URL：" + infoBo.title)
            }
        }
    }

}