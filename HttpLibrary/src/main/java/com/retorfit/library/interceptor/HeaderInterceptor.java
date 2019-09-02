package com.retorfit.library.interceptor;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:54
 * @Desc :请求拦截器  统一添加请求头使用
 * ====================================================
 */
public abstract class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Map<String, String> headers = buildHeaders();
        if (headers == null || headers.isEmpty()) {
            return chain.proceed(request);
        } else {
            Response response = chain.proceed(request.newBuilder()
                    .headers(buildHeaders(request, headers))
                    .build());
            return response;
        }

    }

    private Headers buildHeaders(Request request, Map<String, String> headerMap) {
        Headers headers = request.headers();
        if (headers != null) {
            Headers.Builder builder = headers.newBuilder();
            for (String key : headerMap.keySet()) {
                builder.add(key, headerMap.get(key));
            }
            return builder.build();
        } else {
            return headers;
        }
    }

    public abstract Map<String, String> buildHeaders();
}
