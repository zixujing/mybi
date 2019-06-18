package com.util;

import com.config.Config;

public class RestResult {
    private int flag;
    private String code;
    private String message;
    private Object result;

    private RestResult(){

    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static RestResult SUCCESS_REST_RESULT(Object result){
        RestResult restResult=new RestResult();
        restResult.setFlag(1);
        restResult.setResult(result);
        return restResult;
    }
    public static RestResult FAIL_REST_RESULT(String code){
        RestResult restResult=new RestResult();
        restResult.setFlag(0);
        restResult.setCode(code);
        restResult.setMessage(Config.ERROR.get(code));
        return restResult;
    }

}
