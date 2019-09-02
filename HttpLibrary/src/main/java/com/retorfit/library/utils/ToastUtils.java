package com.retorfit.library.utils;

import android.widget.Toast;
import com.retorfit.library.RxHttp;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 9:41
 * @Desc :Toast工具类
 * ====================================================
 */

public class ToastUtils {

    private static Toast mToast;

    /**
     * Toast提示
     *
     * @param msg 提示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(RxHttp.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
