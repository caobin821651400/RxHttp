package com.app.kotlin.rxhttp.bean;

import android.text.TextUtils;
import com.retorfit.library.base.BaseObserver;
import com.retorfit.library.utils.ToastUtils;
import io.reactivex.disposables.Disposable;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:49
 * @Desc :定义标准的请求返回类型，用于请求成功但是code异常情况
 * ====================================================
 */
public abstract class DataObserver<T> extends BaseObserver<BaseData<T>> {
    /**
     * 成功回调
     *
     * @param data 结果
     */
    protected abstract void onSuccess(T data);

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
    public void doOnNext(BaseData<T> data) {
        onSuccess(data.getResult());
        //可以根据需求对code统一处理
//        switch (data.getCode()) {
//            case 200:
//                onSuccess(data.getData());
//                break;
//            case 300:
//            case 500:
//                onError(data.getMsg());
//                break;
//            default:
//        }
    }

    @Override
    public void doOnCompleted() {
    }


}
