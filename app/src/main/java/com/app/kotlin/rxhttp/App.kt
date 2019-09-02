package com.app.kotlin.rxhttp

import android.app.Application
import com.retorfit.library.RxHttp
import com.retorfit.library.config.OkHttpConfig
import okhttp3.OkHttpClient

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        RxHttp
            .getInstance()
            .init(this)
            .config()
            //自定义factory的用法
            //.setCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.setConverterFactory(ScalarsConverterFactory.create(),GsonConverterFactory.create(GsonAdapter.buildGson()))
            //配置全局baseUrl
            .setBaseUrl("http://v.juhe.cn/")
            //开启全局配置
            .setOkClient(createOkHttp())

    }

    private fun createOkHttp(): OkHttpClient {
        //        获取证书
        //        InputStream cerInputStream = null;
        //        InputStream bksInputStream = null;
        //        try {
        //            cerInputStream = getAssets().open("YourSSL.cer");
        //            bksInputStream = getAssets().open("your.bks");
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        return OkHttpConfig.Builder(this)
            //添加公共请求头
//            .setHeaders {
//                val hashMap = HashMap<String, String>()
//                hashMap["User-Agent"] = "phone/3.0"
//                hashMap
//            }
            //添加自定义拦截器
//            .setAddInterceptor(UploadInterceptor())
            //开启缓存策略(默认false)
            //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
            //2、在没有网络的时候，去读缓存中的数据。
//            .setCache(true)
            .setHasNetCacheTime(10)//默认有网络时候缓存60秒
            //全局持久话cookie,保存到内存（new MemoryCookieStore()）或者保存到本地（new SPCookieStore(this)）
            //不设置的话，默认不对cookie做处理
//            .setCookieType(SPCookieStore(this))
            //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
            //.setAddInterceptor(null)
            //全局ssl证书认证
            //1、信任所有证书,不安全有风险（默认信任所有证书）
            //.setSslSocketFactory()
            //2、使用预埋证书，校验服务端证书（自签名证书）
            //.setSslSocketFactory(cerInputStream)
            //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
            //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
            //设置Hostname校验规则，默认实现返回true，需要时候传入相应校验规则即可
            //.setHostnameVerifier(null)
            //全局超时配置
            .setReadTimeout(10)
            //全局超时配置
            .setWriteTimeout(10)
            //全局超时配置
            .setConnectTimeout(10)
            //全局是否打开请求log日志
            .setDebug(BuildConfig.DEBUG)
            .build()
    }
}