package com.xiaoju.ibt.merchant.exception;

/**
 * @Author: xingrufei
 * @CreateTime: 2022-08-17
 */
public class PayException extends RuntimeException{

    public PayException(String message) {
        super(message);
    }

    public PayException(String message, Throwable cause) {
        super(message, cause);
    }
}
