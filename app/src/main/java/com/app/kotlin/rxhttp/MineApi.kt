package com.app.kotlin.rxhttp

import com.app.kotlin.rxhttp.bean.BaseData
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface MineApi {

    @Headers("caobin:123456", "caobin2:22")
    @FormUrlEncoded
    @POST("toutiao/index")
//    fun getNews(@FieldMap map: Map<String, String>): Observable<BaseData<NewsResp>>
    fun getNews(@Field("type") type: String, @Field("key") key: String): Observable<BaseData<NewsResp>>

    /**
     * get
     */
    @Headers("User-Agent: phone/3.0")
    @GET("api/contents/search")
    fun getImage(@QueryMap map: Map<String, String>): Observable<String>//很多参数可以用@QueryMap
//    fun getImage(@Query("key") value: String): Observable<String>//简单参数用这种@Query


    @POST("mq/interactiveLogSender.htm")
    fun maidian(@Body body: RequestBody): Observable<String>
}