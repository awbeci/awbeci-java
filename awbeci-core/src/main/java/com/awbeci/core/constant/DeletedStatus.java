package com.awbeci.core.constant;

/**
 * 数据删除状态
 * Created by [Sun Wang] on 2015/10/30.
 */
public interface DeletedStatus {

    Integer NORMAL = 0;     //正常
    Integer RECYCLE = 1;    //回收站删除
    Integer DELETED = 2;    //彻底删除

}
