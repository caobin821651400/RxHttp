package com.retorfit.library.retrofit;

import com.retorfit.library.gson.GsonAdapter;
import com.retorfit.library.interceptor.RxHttpLogger;
import com.retorfit.library.interceptor.RxLogInterceptor;
import com.retorfit.library.utils.SSLUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:47
 * @Desc :为上传单独建一个retrofit
 * ====================================================
 */
public class RetrofitBuilder {

    private String baseUrl;

    private CallAdapter.Factory[] callAdapterFactory;

    private Converter.Factory[] converterFactory;

    private OkHttpClient okHttpClient;

    public RetrofitBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RetrofitBuilder setCallAdapterFactory(CallAdapter.Factory... callAdapterFactory) {
        this.callAdapterFactory = callAdapterFactory;
        return this;
    }

    public RetrofitBuilder setConverterFactory(Converter.Factory... converterFactory) {
        this.converterFactory = converterFactory;
        return this;
    }

    public RetrofitBuilder setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }


    public Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(baseUrl);

        if (callAdapterFactory == null || callAdapterFactory.length <= 0) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        } else {
            for (CallAdapter.Factory factory : callAdapterFactory) {
                builder.addCallAdapterFactory(factory);
            }
        }

        if (converterFactory == null || converterFactory.length <= 0) {
            builder.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()));
        } else {
            for (Converter.Factory factory : converterFactory) {
                builder.addConverterFactory(factory);
            }
        }

        if (okHttpClient == null) {
            builder.client(createOkHttpClient());
        } else {
            builder.client(okHttpClient);
        }

        return builder.build();
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);

        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        RxLogInterceptor logInterceptor = new RxLogInterceptor(new RxHttpLogger());
        builder.addInterceptor(logInterceptor);

        return builder.build();
    }
}
