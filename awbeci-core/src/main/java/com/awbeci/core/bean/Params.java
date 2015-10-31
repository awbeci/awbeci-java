package com.awbeci.core.bean;

import java.util.HashMap;

/**
 * 自定义参数集方法
 * Created by [Sun Wang] on 2015/10/30.
 */
public class Params extends HashMap<String, Object> {

    /**
     * 无参构造
     */
    public Params() {
        super();
    }

    /**
     * 构造方法
     * @param key 参数名
     * @param value 参数值
     */
    public Params(String key, Object value) {
        super();
        this.data(key, value);
    }

    /**
     * 设置参数
     * @param key
     * @param value
     * @return 返回参数集对象便于连调使用
     */
    public Params data(String key, Object value) {
        if(key != null && !"".equals(key.trim())) {
            this.put(key, value);
        }
        return this;
    }

}
