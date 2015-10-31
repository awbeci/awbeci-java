package com.awbeci.core.util;

import com.awbeci.core.abs.BaseException;
import com.awbeci.core.fault.ApplicationException;
import com.awbeci.core.fault.BusinessException;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具
 * Created by [Sun Wang] on 2015/10/30.
 */
public class AssertUtil {

    /**
     * 断言对象为非null
     *
     * @param object
     * @param msg    异常消息
     * @param args   构造CoreException实例所需要的参数（最后一个除外），最后一个boolean类型参数表示是否抛出BusinessException异常
     */
    public static void notNull(Object object, String msg, Object... args) {
        if (object == null) throw getException(msg, args);
    }

    /**
     * 断言对象是否为非empty
     *
     * @param object
     * @param msg        异常消息
     * @param args       构造CoreException实例所需要的参数（最后一个除外），最后一个boolean类型参数表示是否抛出BusinessException异常
     */
    public static void notEmpty(Object object, String msg, Object... args) {
        notNull(object, msg, args);
        if(object instanceof Collection) {
            if(((Collection) object).size() == 0) throw getException(msg, args);
        } else if(object instanceof Object[]) {
            if(((Object[]) object).length == 0) throw getException(msg, args);
        } else if(object instanceof Map) {
            if(((Map) object).isEmpty()) throw getException(msg, args);
        } else if(object instanceof CharSequence) {
            if(object.toString().length() == 0) throw getException(msg, args);
        }
    }

    /**
     * 获取异常实例
     *
     * @param msg
     * @param args
     * @return
     */
    private static BaseException getException(String msg, Object... args) {
        Boolean isBusinessException = false;
        if (args != null && args.length > 0) {
            Class<?> type = args[args.length - 1].getClass();
            if (Boolean.class.equals(type) || Boolean.TYPE.equals(type)) {
                isBusinessException = (Boolean) args[args.length - 1];
            }
        }
        return isBusinessException ? new BusinessException(msg, args) : new ApplicationException(msg, args);
    }

}

