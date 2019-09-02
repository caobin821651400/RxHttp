package com.retorfit.library;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import com.retorfit.library.config.OkHttpConfig;
import com.retorfit.library.cookie.CookieJarImpl;
import com.retorfit.library.cookie.store.CookieStore;
import com.retorfit.library.download.DownloadHelper;
import com.retorfit.library.factory.ApiFactory;
import com.retorfit.library.manage.RxHttpManager;
import com.retorfit.library.upload.UploadHelper;
import com.retorfit.library.upload.UploadProgressListener;
import io.reactivex.Observable;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:38
 * @Desc :网络请求
 * ====================================================
 */
public class RxHttp {

    @SuppressLint("StaticFieldLeak")
    private static RxHttp instance;
    @SuppressLint("StaticFieldLeak")
    private static Application context;

    public static RxHttp getInstance() {
        if (instance == null) {
            synchronized (RxHttp.class) {
                if (instance == null) {
                    instance = new RxHttp();
                }
            }

        }
        return instance;
    }


    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public RxHttp init(Application app) {
        context = app;
        return this;
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        checkInitialize();
        return context;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttp.getInstance().init(this) 初始化！");
        }
    }


    public ApiFactory config() {
        checkInitialize();
        return ApiFactory.getInstance();
    }


    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls) {
        return ApiFactory.getInstance().createApi(cls);
    }

    /**
     * 切换baseUrl
     *
     * @param baseUrlKey   域名的key
     * @param baseUrlValue 域名的url
     * @param cls          class
     * @param <K>          k
     * @return k
     */
    public static <K> K createApi(String baseUrlKey, String baseUrlValue, Class<K> cls) {
        return ApiFactory.getInstance().createApi(baseUrlKey, baseUrlValue, cls);
    }


    /**
     * 下载文件
     *
     * @param fileUrl 地址
     * @return ResponseBody
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadHelper.downloadFile(fileUrl);
    }

    /**
     * 上传单张图片
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath, UploadProgressListener listener) {
        return UploadHelper.uploadImage(uploadUrl, filePath,listener);
    }


    /**
     * 上传单张图片带参数
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImgParams(String uploadUrl, String filePath, Map<String, String> paramsMap,UploadProgressListener listener) {
        return UploadHelper.uploadImageWithParams(uploadUrl, filePath, paramsMap,listener);

    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImages(String uploadUrl, List<String> filePaths,UploadProgressListener listener) {
        return UploadHelper.uploadImages(uploadUrl, filePaths,listener);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param fileName  后台接收文件流的参数名
     * @param paramsMap 参数
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImagesWithParams(
            String uploadUrl, String fileName, Map<String, String> paramsMap, List<String> filePaths,UploadProgressListener listener) {
        return UploadHelper.uploadFilesWithParams(uploadUrl, fileName, paramsMap, filePaths,listener);
    }

    /**
     * 获取全局的CookieJarImpl实例
     */
    private static CookieJarImpl getCookieJar() {
        return (CookieJarImpl) OkHttpConfig.getInstance().getOkHttpClient().cookieJar();
    }

    /**
     * 获取全局的CookieStore实例
     */
    private static CookieStore getCookieStore() {
        return getCookieJar().getCookieStore();
    }

    /**
     * 获取所有cookie
     */
    public static List<Cookie> getAllCookie() {
        CookieStore cookieStore = getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        return allCookie;
    }

    /**
     * 获取某个url所对应的全部cookie
     */
    public static List<Cookie> getCookieByUrl(String url) {
        CookieStore cookieStore = getCookieStore();
        HttpUrl httpUrl = HttpUrl.parse(url);
        List<Cookie> cookies = cookieStore.getCookie(httpUrl);
        return cookies;
    }


    /**
     * 移除全部cookie
     */
    public static void removeAllCookie() {
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeAllCookie();
    }

    /**
     * 移除某个url下的全部cookie
     */
    public static void removeCookieByUrl(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeCookie(httpUrl);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAll() {
        RxHttpManager.get().cancelAll();
    }

    /**
     * 取消某个或某些请求
     */
    public static void cancel(Object... tag) {
        RxHttpManager.get().cancel(tag);
    }
}
