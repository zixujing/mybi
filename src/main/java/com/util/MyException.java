package com.util;

public class MyException extends Exception {
    private String errorCode;
    private MyException(String code){
        this.errorCode=code;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public static MyException GET_EXCEPTION(String code){
        return new MyException(code);
    }

}
