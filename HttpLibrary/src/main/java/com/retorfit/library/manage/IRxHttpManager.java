package com.retorfit.library.manage;

import io.reactivex.disposables.Disposable;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:50
 * @Desc :请求管理接口
 * ====================================================
 */
public interface IRxHttpManager<T> {

    /**
     * 添加
     *
     * @param tag        tag
     * @param disposable disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除请求
     *
     * @param tag tag
     */
    void remove(T tag);

    /**
     * 取消某个tag的请求
     *
     * @param tag tag
     */
    void cancel(T tag);

    /**
     * 取消某些tag的请求
     *
     * @param tags tags
     */
    void cancel(T... tags);

    /**
     * 取消所有请求
     */
    void cancelAll();
}
