package com.bjpowernode.web.exception;

//只有RuntimeException可以支持事务
public class MyLoginException extends RuntimeException {
    public MyLoginException(String message) {
        super(message);
    }
}