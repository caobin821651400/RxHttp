package com.retorfit.library.base;

import com.retorfit.library.exception.ApiException;
import com.retorfit.library.interfaces.ISubscriber;
import com.retorfit.library.manage.RxHttpManager;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 10:12
 * @Desc :基类BaseObserver
 * ====================================================
 */
public abstract class BaseObserver<T> implements Observer<T>, ISubscriber<T> {

    /**
     * 是否隐藏toast
     *
     * @return
     */
    protected boolean isHideToast() {
        return false;
    }

    /**
     * 标记网络请求的tag
     * tag下的一组或一个请求，用来处理一个页面的所以请求或者某个请求
     * 设置一个tag就行就可以取消当前页面所有请求或者某个请求了
     *
     * @return string
     */
    protected String setTag() {
        return null;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        RxHttpManager.get().add(setTag(), d);
        doOnSubscribe(d);
    }

    @Override
    public void onNext(@NonNull T t) {
        doOnNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        setError(error);
    }


    @Override
    public void onComplete() {
        doOnCompleted();
    }

    private void setError(String errorMsg) {
        doOnError(errorMsg);
    }
}
