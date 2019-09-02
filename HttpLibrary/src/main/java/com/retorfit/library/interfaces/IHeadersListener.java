package com.retorfit.library.interfaces;

import java.util.Map;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:52
 * @Desc :请求头interface
 * ====================================================
 */
public interface IHeadersListener {
    Map<String, String> addHeaders();
}
