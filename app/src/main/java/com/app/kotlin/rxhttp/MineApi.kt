package com.app.kotlin.rxhttp

import com.app.kotlin.rxhttp.bean.BaseData
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface MineApi {

    @Headers("caobin:123456")
    @FormUrlEncoded
    @POST("toutiao/index")
    fun getNews(@FieldMap map: Map<String, String>): Observable<BaseData<NewsResp>>


    /**
     * 测试postJson方式
     */
    @Headers("Content-Type: application/json")
    @POST("")
    fun postJson(@Body body: RequestBody): Observable<String>

    /**
     * get
     */
    @Headers("User-Agent: phone/3.0")
    @GET("api/contents/search")
    fun getImage(@QueryMap map: Map<String, String>): Observable<String>


    @POST("mq/interactiveLogSender.htm")
    fun maidian(@Body body: RequestBody): Observable<String>
}