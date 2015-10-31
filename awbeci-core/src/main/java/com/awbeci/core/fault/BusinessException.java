package com.awbeci.core.fault;

/**
 * Created by [Sun Wang] on 2015/10/30.
 */
public class BusinessException extends ApplicationException {

    /**
     * 无参构造
     */
    public BusinessException() {
        super();
    }

    /**
     * 带参构造
     * @param msg 异常消息，可以使用{}为占位符
     * @param args 占位符参数和真实异常信息，其中最后一个参数可以是真实异常
     */
    public BusinessException(String msg, Object... args) {
        super(msg, args);
    }

}
