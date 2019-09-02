package com.retorfit.library.interfaces;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:53
 * @Desc :接口化处理loadingView，突破之前只能用dialog的局限
 * ====================================================
 */
public interface ILoadingView {
    /**
     * 显示loadingView
     */
    void showLoadingView();

    /**
     * 隐藏loadingView
     */
    void hideLoadingView();
}
