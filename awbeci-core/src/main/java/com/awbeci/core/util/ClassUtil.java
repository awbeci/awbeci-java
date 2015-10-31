package com.awbeci.core.util;

import com.awbeci.core.fault.ApplicationException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;

/**
 * 类型相关工具
 * Created by [Sun Wang] on 2015/10/31.
 */
public class ClassUtil {

    /**
     * 获取当前线程的ClassLoader
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取当前线程classpath路径
     *
     * @return
     */
    public static String getClassPath() {
        URL resource = getClassLoader().getResource("");
        return resource != null ? resource.getPath() : "";
    }

    /**
     * 加载class
     *
     * @param type
     * @return
     */
    public static Class<?> loadClass(String type) {
        return loadClass(type, true);
    }

    /**
     * 加载class
     *
     * @param type
     * @param initialize
     * @return
     */
    public static Class<?> loadClass(String type, boolean initialize) {
        try {
            return Class.forName(type, initialize, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new ApplicationException("加载类时出错：[ {} ]", type, e);
        }
    }

    /**
     * 无参构造实例化
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<? extends T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new ApplicationException("实例化类时出错：[ {} ]", type, e);
        }
    }

    /**
     * 获取泛型参数类型
     *
     * @param type
     * @return
     */
    public static Class<?> getGenericType(Class<?> type) {
        return getGenericType(type, 0);
    }

    /**
     * 获取泛型参数类型
     *
     * @param type
     * @param index
     * @return
     */
    public static Class<?> getGenericType(Class<?> type, final int index) {
        Class<?> clazz = Object.class;
        Type genType = type.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] genTypes = ((ParameterizedType) genType).getActualTypeArguments();
            if (genTypes != null && index >= 0 && index < genTypes.length) {
                clazz = (Class<?>) genTypes[index];
            }
        }
        return clazz;
    }

    /**
     * 是否是Integer或int类型
     *
     * @param type
     * @return
     */
    public static boolean isInt(Class<?> type) {
        return Integer.TYPE.equals(type) || Integer.class.equals(type);
    }

    /**
     * 是否是Long或long类型
     *
     * @param type
     * @return
     */
    public static boolean isLong(Class<?> type) {
        return Long.TYPE.equals(type) || Long.class.equals(type);
    }

    /**
     * 是否是Float或float类型
     *
     * @param type
     * @return
     */
    public static boolean isFloat(Class<?> type) {
        return Float.TYPE.equals(type) || Float.class.equals(type);
    }

    /**
     * 是否是Double或double类型
     *
     * @param type
     * @return
     */
    public static boolean isDouble(Class<?> type) {
        return Double.TYPE.equals(type) || Double.class.equals(type);
    }

    /**
     * 是否是Boolean或boolean类型
     *
     * @param type
     * @return
     */
    public static boolean isBoolean(Class<?> type) {
        return Boolean.TYPE.equals(type) || Boolean.class.equals(type);
    }

    /**
     * 是否是Short或short类型
     *
     * @param type
     * @return
     */
    public static boolean isShort(Class<?> type) {
        return Short.TYPE.equals(type) || Short.class.equals(type);
    }

    /**
     * 是否是Byte或byte类型
     *
     * @param type
     * @return
     */
    public static boolean isByte(Class<?> type) {
        return Byte.TYPE.equals(type) || Byte.class.equals(type);
    }

    /**
     * 是否是Character或char类型
     *
     * @param type
     * @return
     */
    public static boolean isChar(Class<?> type) {
        return Character.TYPE.equals(type) || Character.class.equals(type);
    }

    /**
     * 是否是String类型
     *
     * @param type
     * @return
     */
    public static boolean isString(Class<?> type) {
        return String.class.equals(type);
    }

    /**
     * 是否是基本类型
     *
     * @param type
     * @return
     */
    public static boolean isBasicType(Class<?> type) {
        return Integer.TYPE.equals(type)
                || Long.TYPE.equals(type)
                || Float.TYPE.equals(type)
                || Double.TYPE.equals(type)
                || Boolean.TYPE.equals(type)
                || Short.TYPE.equals(type)
                || Byte.TYPE.equals(type)
                || Character.TYPE.equals(type);
    }

}