package com.app.ymyapp.api

import com.app.ymyapp.base.BaseResponse
import com.app.ymyapp.bean.ImageBo
import com.app.ymyapp.bean.InfosBo
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author:create by ys
 * 时间:2021/5/24 16
 * 邮箱 894417048@qq.com
 */
interface ApiService {

    @FormUrlEncoded
    @POST("getImages")
    fun getImages(@FieldMap params: HashMap<String, Any>): Observable<BaseResponse<List<ImageBo>>>

    @FormUrlEncoded
    @POST("getWangYiNews")
    fun getWangYiNews(@FieldMap params: HashMap<String, Any>): Observable<BaseResponse<List<InfosBo>>>
}