package com.retorfit.library.upload;


import com.retorfit.library.factory.ApiFactory;
import io.reactivex.Observable;
import okhttp3.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:47
 * @Desc :为上传单独建一个retrofit
 * ====================================================
 */
public class UploadHelper {

    /**
     * 上传一张图片不带参数
     *
     * @param uploadUrl 上传图片的服务器url
     * @param filePath  图片路径
     * @return Observable
     */
    public static Observable<ResponseBody> uploadImage(String uploadUrl, String filePath, UploadProgressListener listener) {
        List<String> filePaths = new ArrayList<>();
        filePaths.add(filePath);
        return uploadFilesWithParams(uploadUrl, "uploaded_file", null, filePaths, listener);
    }

    /**
     * 上传一张图片带参数
     *
     * @param uploadUrl 上传图片的服务器url
     * @param filePath  图片路径
     * @return Observable
     */
    public static Observable<ResponseBody> uploadImageWithParams(String uploadUrl, String filePath, Map<String, String> paramsMap, UploadProgressListener listener) {
        List<String> filePaths = new ArrayList<>();
        filePaths.add(filePath);
        return uploadFilesWithParams(uploadUrl, "uploaded_file", paramsMap, filePaths, listener);
    }

    /**
     * 只上传图片
     *
     * @param uploadUrl 上传图片的服务器url
     * @param filePaths 图片路径
     * @return Observable
     */
    public static Observable<ResponseBody> uploadImages(String uploadUrl, List<String> filePaths, UploadProgressListener listener) {
        return uploadFilesWithParams(uploadUrl, "uploaded_file", null, filePaths, listener);
    }

    /**
     * 图片和参数同时上传的请求
     *
     * @param uploadUrl 上传图片的服务器url
     * @param fileName  后台协定的接受图片的name（没特殊要求就可以随便写）
     * @param paramsMap 普通参数
     * @param filePaths 图片路径
     * @return Observable
     */
    public static Observable<ResponseBody> uploadFilesWithParams(
            String uploadUrl, String fileName, Map<String, String> paramsMap, List<String> filePaths, UploadProgressListener listener) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if (null != paramsMap) {
            for (String key : paramsMap.keySet()) {
                builder.addFormDataPart(key, (String) paramsMap.get(key));
            }
        }

        for (int i = 0; i < filePaths.size(); i++) {
            File file = new File(filePaths.get(i));
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            //"fileName"+i 后台接收图片流的参数名
            builder.addFormDataPart(fileName, file.getName(), imageBody);
        }

        List<MultipartBody.Part> parts = builder.build().parts();

        String DEFAULT_UPLOAD_KEY = "defaultUploadUrlKey";
        String DEFAULT_BASE_URL = "https://api.github.com/";

        return ApiFactory.getInstance()
                .setOkClient(new OkHttpClient.Builder().addInterceptor(new UploadInterceptor(listener)).build())
                .createApi(DEFAULT_UPLOAD_KEY, DEFAULT_BASE_URL, UploadFileApi.class)
                .uploadFiles(uploadUrl, parts);
    }
}
