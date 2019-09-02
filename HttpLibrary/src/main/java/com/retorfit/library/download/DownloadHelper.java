package com.retorfit.library.download;


import com.retorfit.library.factory.ApiFactory;
import com.retorfit.library.interceptor.Transformer;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 10:03
 * @Desc :为下载单独建一个retrofit
 * ====================================================
 */
public class DownloadHelper {
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        String DEFAULT_DOWNLOAD_KEY = "defaultDownloadUrlKey";
        String DEFAULT_BASE_URL = "https://api.github.com/";
        return ApiFactory.getInstance()
                .setOkClient(new OkHttpClient.Builder().addInterceptor(new DownloadInterceptor()).build())
                .createApi(DEFAULT_DOWNLOAD_KEY, DEFAULT_BASE_URL, DownloadApi.class)
                .downloadFile(fileUrl)
                .compose(Transformer.<ResponseBody>switchSchedulers());
    }
}
