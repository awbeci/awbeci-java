package com.awbeci.core.abs;

import com.awbeci.core.IParams;
import com.awbeci.core.bean.Params;
import com.awbeci.core.util.ReflectionUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 抽象实体类
 * Created by Sunw on 2015/10/30.
 */
public abstract class BaseModel implements Serializable, IParams {

    /**
     * 实体转换成参数集对象
     * @return
     */
    @Override
    public Params toParams() {
        Map<String, Field> fieldMap = ReflectionUtil.getBeanFields(this.getClass());
        Set<String> keys = fieldMap.keySet();
        Params params = new Params();
        for(String key : keys) {
            params.data(key, ReflectionUtil.getFieldValue(this, fieldMap.get(key)));
        }
        return params;
    }

}
