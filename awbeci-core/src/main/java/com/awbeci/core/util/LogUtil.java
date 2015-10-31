package com.awbeci.core.util;

import org.slf4j.LoggerFactory;

/**
 * 基于slf4j的日志打印工具
 * Created by [Sun Wang] on 2015/10/30.
 */
public class LogUtil {

    /**
     * error级别日志
     * @param clazz 指定日志输出者类型
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void error(Class clazz, String msg, Object... args) {
        LoggerFactory.getLogger(clazz).error(msg, args);
    }

    /**
     * error级别日志
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void error(String msg, Object... args) {
        error(getCallerType(), msg, args);
    }

    /**
     * warn级别日志
     * @param clazz 指定日志输出者类型
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void warn(Class clazz, String msg, Object... args) {
        LoggerFactory.getLogger(clazz).warn(msg, args);
    }

    /**
     * warn级别日志
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void warn(String msg, Object... args) {
        warn(getCallerType(), msg, args);
    }

    /**
     * info级别日志
     * @param clazz 指定日志输出者类型
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void info(Class clazz, String msg, Object... args) {
        LoggerFactory.getLogger(clazz).info(msg, args);
    }

    /**
     * info级别日志
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void info(String msg, Object... args) {
        info(getCallerType(), msg, args);
    }

    /**
     * debug级别日志
     * @param clazz 指定日志输出者类型
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void debug(Class clazz, String msg, Object... args) {
        LoggerFactory.getLogger(clazz).debug(msg, args);
    }

    /**
     * debug级别日志
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void debug(String msg, Object... args) {
        debug(getCallerType(), msg, args);
    }

    /**
     * trace级别日志
     * @param clazz 指定日志输出者类型
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void trace(Class clazz, String msg, Object... args) {
        LoggerFactory.getLogger(clazz).trace(msg, args);
    }

    /**
     * trace级别日志
     * @param msg 日志消息，可使用{}为占位符
     * @param args 消息占位符参数值，最后一个参数可以是异常实例
     */
    public static void trace(String msg, Object... args) {
        trace(getCallerType(), msg, args);
    }

    /**
     * 获取调用者类型
     * @return 返回调用者类型
     */
    private static Class getCallerType() {
        Class clazz = LogUtil.class;
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : stes) {
            try {
                clazz = Class.forName(ste.getClassName());
                if (!Thread.class.equals(clazz)
                        && !LogUtil.class.equals(clazz)) {
                    return clazz;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }

}
