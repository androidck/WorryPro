package com.worry.common.result;

/**
 * 统一数据处理
 * @param <T>
 */
public class Result<T> {

    private int code;//状态码

    private String msg;//消息内容

    private T data;//数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
