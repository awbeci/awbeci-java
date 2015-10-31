package com.awbeci.core;

import com.awbeci.core.bean.Params;

/**
 * 参数集转换接口
 * Created by [Sun Wang] on 2015/10/30.
 */
public interface IParams {

    /**
     * 转换成参数集对象
     * @return
     */
    Params toParams();

}
