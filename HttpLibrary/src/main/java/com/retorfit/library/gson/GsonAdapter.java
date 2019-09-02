package com.retorfit.library.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:58
 * @Desc :防止后台返回类型不匹配情况的工厂方法
 * ====================================================
 */
public class GsonAdapter {

    public static Gson buildGson() {

        return new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .create();
    }
}
