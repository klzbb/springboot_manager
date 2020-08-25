package com.konglingzhan.manager.common.exception;

import com.konglingzhan.manager.vo.CodeMsg;

public class CommonException extends RuntimeException{
    private int code;
    private String msg;

    public CommonException( int code) {
        this.code = code;
        this.msg = CodeMsg.getMessage(code);
    }

    public CommonException( int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
