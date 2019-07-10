package com.util;

import com.config.Config;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public RestResult constraintViolationExpFunction(ConstraintViolationException constraionVE){
        String message= Config.EC___DEFAULT;
        for (ConstraintViolation cv:constraionVE.getConstraintViolations()){
            //取最后一个
            message=cv.getMessage();
        }
        return RestResult.FAIL_REST_RESULT(message);
    }

    @ExceptionHandler(value = MyException.class)
    public RestResult myExceptionFunction(MyException myE){
        return RestResult.FAIL_REST_RESULT(myE.getErrorCode());
    }

    //@ExceptionHandler(value = UnexpectedTypeException.class)
   // public RestResult unexpectedTypeExeFunction(UnexpectedTypeException upe){
    //    return RestResult.FAIL_REST_RESULT(upe.getMessage());
    //}

}
