package com.bjpowernode.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LoginExceptionController {
    @ExceptionHandler(MyLoginException.class)
    @ResponseBody
    public Map MyException(MyLoginException e){
        Map map = new HashMap(){{
            put("msg",e.getMessage());
        }};
        return map;
    }
}
