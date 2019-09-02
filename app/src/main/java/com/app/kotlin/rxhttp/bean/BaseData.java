package com.app.kotlin.rxhttp.bean;

/**
 * ====================================================
 *
 * @User :caobin
 * @Date :2019/8/21 10:11
 * @Desc :返回数据基类
 * ====================================================
 */
public class BaseData<T> {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误描述
     */
    private String reason;

    /**
     * 数据
     */
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "code=" + code +
                ", msg='" + reason + '\'' +
                ", data=" + result +
                '}';
    }
}
