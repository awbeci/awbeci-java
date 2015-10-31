package com.awbeci.core.util;

import java.util.Collection;
import java.util.Map;

/**
 * 常用工具
 * Created by [Sun Wang] on 2015/10/31.
 */
public class CommonUtil {

    /**
     * 判断字符串是否为空白，Map集合、Collection集合、数组对象是否为空集合/空数组
     *
     * @param arg
     * @return
     */
    public static boolean isEmpty(Object arg) {
        if(arg == null) return true;
        if(arg instanceof Collection) {
            return ((Collection) arg).size() == 0;
        } else if(arg instanceof Object[]) {
            return ((Object[]) arg).length == 0;
        } else if(arg instanceof CharSequence) {
            return arg.toString().trim().length() == 0;
        } else if(arg instanceof Map) {
            return ((Map) arg).isEmpty();
        }
        return false;
    }

    /**
     * 仿slf4j字符串格式化方法
     * @param string
     * @param args
     * @return
     */
    public static String formatString(String string, Object...args) {
        if(string == null) return null;
        String[] array = string.split("\\{\\}");
        StringBuffer sb = new StringBuffer();
        for(int i = 0, l = array.length; i < l; i++) {
            sb.append(array[i]);
            if(args != null && args.length > i) {
                sb.append(args[i]);
            }
        }
        return sb.toString();
    }

}

