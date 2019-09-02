package com.retorfit.library.upload;

import android.os.Handler;
import android.os.Looper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class UploadInterceptor implements Interceptor {
    private UploadProgressListener listener;

    public UploadInterceptor(UploadProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request build = request.newBuilder()
                .addHeader("User-Agent", "phone/3.0")
                .method(request.method(), new ProgressRequestBody(new Handler(Looper.getMainLooper()), request.body(),
                        listener, 180))//进度
                .build();
        Response response = chain.proceed(build);
        return response;
    }
}
