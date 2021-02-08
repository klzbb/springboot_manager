package com.konglingzhan.manager.vo;


public class Result<T> {

    private String msg;
    private int code;
    private T data;


    public Result(int code,String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code,String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     * 成功时候的调用
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(CodeMsg.CODE_SUCCESS,"success",data);
    }


    /**
     * 成功，不需要传入参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success(){
        return (Result<T>) success("");
    }

    /**
     * 失败时候的调用
     * @param msg
     * @return
     */
    public static <T> Result<T> error(String msg){
        return new Result<T>(CodeMsg.CODE_FAIL,msg,null);
    }


    /**
     * 失败，不传参数的时候
     * @return
     */
    public static <T> Result<T> error(){
        return new Result<T>(CodeMsg.CODE_FAIL,"接口调用失败",null);
    }
    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
    public int getCode() {
        return code;
    }
}
