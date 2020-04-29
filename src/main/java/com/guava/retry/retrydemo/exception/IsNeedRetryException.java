package com.guava.retry.retrydemo.exception;

/**
 * @author vangoleo
 * @create 2020-04-27 2:50 下午
 * @desc 需要重试的异常
 **/
public class IsNeedRetryException extends Exception{

    public IsNeedRetryException(String message){
        super("IsNeedRetryException can retry "+message);
    }
}