package com.retorfit.library.observer;

import android.text.TextUtils;
import com.retorfit.library.base.BaseObserver;
import com.retorfit.library.utils.ToastUtils;
import io.reactivex.disposables.Disposable;


/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:50
 * @Desc :处理string回调
 * ====================================================
 */
public abstract class StringObserver extends BaseObserver<String> {

    /**
     * 成功回调
     *
     * @param data 结果
     */
    protected abstract void onSuccess(String data);

    /**
     * 失败回调
     *
     * @param errorMsg 错误信息
     */
    protected abstract void onError(String errorMsg);

    @Override
    public void doOnSubscribe(Disposable d) {
    }

    @Override
    public void doOnError(String errorMsg) {
        if (!isHideToast() && !TextUtils.isEmpty(errorMsg)) {
            ToastUtils.showToast(errorMsg);
        }
        onError(errorMsg);
    }

    @Override
    public void doOnNext(String string) {
        onSuccess(string);
    }


    @Override
    public void doOnCompleted() {
    }
}
