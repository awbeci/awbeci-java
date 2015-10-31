package com.awbeci.core.abs;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * 抽象自定义异常类
 * Created by [Sun Wang] on 2015/10/30.
 */
public abstract class BaseException extends RuntimeException {

    private Throwable error;
    private String msg;

    public BaseException() {
        this(null);
    }

    public BaseException(String msg, Object... args) {
        //采用slf4j的日志格式化方法
        FormattingTuple ft = MessageFormatter.arrayFormat(msg, args);
        this.msg = ft.getMessage();
        this.error = ft.getThrowable();
    }

    @Override
    public synchronized Throwable getCause() {
        return error != null ? error : super.getCause();
    }

    @Override
    public String getMessage() {
        if (error != null && error instanceof BaseException) {
            return error.getMessage();
        } else {
            return msg;
        }
    }

}
