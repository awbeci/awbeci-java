package com.awbeci.core.util;

import com.awbeci.core.fault.ApplicationException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 反射工具类
 * Created by [Sun Wang] on 2015/10/31.
 */
public class ReflectionUtil {

    /**
     * 获取Bean的属性集合，包括其父类中的属性
     * @param clazz
     * @return Map：key属性名，value属性
     */
    public static Map<String, Field> getBeanFields(Class clazz) {
        AssertUtil.notNull(clazz, "请指定目标bean类型");
        Map<String, Field> fields = new HashMap<String, Field>();
        Class currentClass = clazz;
        Set<String> fieldNames = new HashSet<String>();
        while(currentClass == null || !Object.class.equals(currentClass)) {
            Field[] array = currentClass.getDeclaredFields();
            for(Field field : array) {
                String fieldName = field.getName();
                //判断子类是否覆盖了此属性
                if(fieldNames.contains(field.getName())) continue;
                fieldNames.add(fieldName);
                //判断属性修饰符
                int modifiers = field.getModifiers();
                if(Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) continue;
                //属性放入集合
                fields.put(fieldName, field);
            }
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 通过反射为属性复制
     * @param obj
     * @param field
     * @param value
     */
    public static void setFieldValue(Object obj, Field field, Object value) {
        AssertUtil.notNull(field, "请指定要赋值的属性");
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new ApplicationException("设置属性值出错：Field:[ {} ]， Class:[ {} ]",
                    field.getName(), (obj == null ? field.getDeclaringClass() : obj.getClass()).getName(), e);
        }
    }

    /**
     * 获取对象属性值
     * @param obj
     * @param field
     * @return
     */
    public static Object getFieldValue(Object obj, Field field) {
        AssertUtil.notNull(field, "请指定要取值的属性");
        try {
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new ApplicationException("获取属性值出错：Field:[ {} ]， Class:[ {} ]",
                    field.getName(), (obj == null ? field.getDeclaringClass() : obj.getClass()).getName(), e);
        }
    }

    /**
     * Bean转Map
     * @param object
     * @return
     */
    public static Map<String, Object> beanToMap(Object object) {
        if(object == null) return null;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Field> fieldMap = getBeanFields(object.getClass());
        Set<String> keys = fieldMap.keySet();
        for(String key : keys) {
            Field field = fieldMap.get(key);
            map.put(key, getFieldValue(object, field));
        }
        return map;
    }

}
