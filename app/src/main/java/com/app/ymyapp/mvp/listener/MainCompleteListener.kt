package com.app.ymyapp.mvp.listener

import com.app.ymyapp.bean.ImageBo
import com.app.ymyapp.bean.InfosBo

/**
 * @author:create by ys
 * 时间:2021/5/25 17
 * 邮箱 894417048@qq.com
 */
interface MainCompleteListener {
    
    fun getImages(imageBos: List<ImageBo>)

    fun getWangYiNews(infosBos: List<InfosBo>)
}