package com.app.kotlin.rxhttp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.kotlin.rxhttp.bean.DataObserver
import com.retorfit.library.RxHttp
import com.retorfit.library.download.DownloadObserver
import com.retorfit.library.interceptor.Transformer
import com.retorfit.library.observer.CommonObserver
import com.retorfit.library.observer.StringObserver
import com.retorfit.library.upload.ProgressInfo
import com.retorfit.library.upload.UploadProgressListener
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject

class TestActivity : AppCompatActivity() {

    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv)
        findViewById<View>(R.id.aaa).setOnClickListener {
                        post()
//            uploadImage()
//            download()
//            upJson()
//            getImageData()
        }
    }

    private fun post() {
        val map = HashMap<String, String>()
        map["type"] = "top"
        map["key"] = "f323c09a114635eb935ed8dd19f7284e"
        RxHttp.createApi(MineApi::class.java)
            .getNews(map)
            .compose(Transformer.switchSchedulers())
            .subscribe(object : DataObserver<NewsResp>() {

                override fun onError(errorMsg: String) {
                }

                override fun onSuccess(data: NewsResp) {
                    System.err.println(data.data?.get(0)?.title)
                }
            })
    }

    /**
     * get方式
     */
    private fun getImageData() {
        val map = HashMap<String, String>()
        map["pages"] = "1"
        map["pageNumbs"] = "2"
        map["tag"] = ""
        map["itvNum"] = "DMT2015122206@ITVP"
        map["contentType"] = "0"
        map["token"] = "MTU2NzA0ODAyOTg2MUM"
        RxHttp.createApi("getImageData", "", MineApi::class.java)
            .getImage(map)
            .compose(Transformer.switchSchedulers())
            .subscribe(object : StringObserver() {
                override fun onSuccess(data: String) {
                    val da = data
                }

                override fun onError(errorMsg: String) {
                }

            })

    }

    /**
     * 上传图片
     */
    private fun uploadImage() {
        val map = HashMap<String, String>()
        map["itvNum"] = "DMT2015122206@ITVP"
        map["contentType"] = "1"
        map["contentId"] = ""
        map["tag"] = "家庭"
        map["desp"] = "1111"
        map["phone"] = "15108460749"
        map["pushMessage"] = "1 "
        map["viewSrc"] = "1"
        map["token"] = "MTU2NzA0ODAyOTg2MUM"
        RxHttp.uploadImgParams(
            "",
            "/storage/emulated/0/DCIM/Camera/IMG_20190828_094425.jpg", map, listener
        )
            .compose(Transformer.switchSchedulers())
            .subscribe(object : CommonObserver<ResponseBody>() {
                override fun onSuccess(t: ResponseBody) {
                    val msg = t.string()
                    Log.e("allen", "上传完毕: $msg")
                }

                override fun onError(errorMsg: String?) {
                }


            })
    }

    /**
     * 下载
     */
    private fun download() {
        RxHttp.downloadFile("/download/DMT2015122206ITVP1DIPMGKAIGK36MP.jpg")
            .subscribe(object : DownloadObserver("图片.jpg", Environment.getExternalStorageDirectory().absolutePath) {
                override fun onError(errorMsg: String?) {
                }

                override fun onSuccess(
                    bytesRead: Long,
                    contentLength: Long,
                    progress: Float,
                    done: Boolean,
                    filePath: String?
                ) {
                    Log.d("AAv ", "下 载中：$progress%")
                    if (done) {
                        Log.d("AAv ", "下载完成：$filePath")
                    }
                }
            })
    }

    /**
     *上传进度监听
     */
    private val listener = object : UploadProgressListener {
        @SuppressLint("SetTextI18n")
        override fun onProgress(progressInfo: ProgressInfo) {
            Log.d("进度 ", "${progressInfo.percent} / 100")
            tv.text="${progressInfo.percent} / 100"
        }

        override fun onError(id: Long, e: Exception?) {
        }

    }

    /**
     * upjson方式
     */
    fun upJson() {
        val jsonObject = JSONObject()
        //加密
        jsonObject.put("boxid", "DMT2015122206@ITVP")
        jsonObject.put("contentLogtype", "1001")
        jsonObject.put("contentId", "")
        jsonObject.put("contentName", "")
        jsonObject.put("contentType", "")
        jsonObject.put("moduleType", "")
        jsonObject.put("phonenum", "")
        jsonObject.put("buttonType", "")
        val params = jsonObject.toString()
        val body = RequestBody.create(MediaType.parse("application/json"), params)

        RxHttp.createApi("upJson", "", MineApi::class.java)
            .maidian(body)
            .compose(Transformer.switchSchedulers())
            .subscribe(object : StringObserver() {
                override fun onSuccess(data: String?) {
                }

                override fun onError(errorMsg: String?) {
                }
            })
    }
}
