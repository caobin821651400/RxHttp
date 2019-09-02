package com.retorfit.library.observer;


import android.text.TextUtils;
import com.retorfit.library.base.BaseObserver;
import com.retorfit.library.utils.ToastUtils;
import io.reactivex.disposables.Disposable;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:47
 * @Desc :用户可以根据自己需求自定义自己的类继承BaseObserver<T>即可
 * ====================================================
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {

    /**
     * 成功回调
     *
     * @param t
     */
    protected abstract void onSuccess(T t);

    /**
     * 失败回调
     *
     * @param errorMsg
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
    public void doOnNext(T t) {
        onSuccess(t);
    }

    @Override
    public void doOnCompleted() {
    }
}
