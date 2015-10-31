package com.awbeci.core.fault.msg;

/**
 * 业务提示消息
 * Created by [Sun Wang] on 2015/10/30.
 */
public interface BusinessMessage {

    /**
     * 数据库消息
     */
    String DB_ADD_NULL = "请指定要添加的数据";
    String DB_ADD_SUCCESS = "添加数据成功";
    String DB_ADD_ERROR = "添加数据失败";
    String DB_UPDATE_NULL = "请指定要修改的数据";
    String DB_UPDATE_SUCCESS = "修改数据成功";
    String DB_UPDATE_ERROR = "修改数据失败";
    String DB_SAVE_NULL = "请指定要保存的数据";
    String DB_SAVE_SUCCESS = "保存数据成功";
    String DB_SAVE_ERROR = "保存数据失败";
    String DB_DEL_NULL = "请指定要删除的数据";
    String DB_DEL_SUCCESS = "删除数据成功";
    String DB_DEL_ERROR = "删除数据失败";
    String DB_QUERY_NULL = "请指定要查询的数据";
    String DB_QUERY_SUCCESS = "查询数据成功";
    String DB_QUERY_ERROR = "查询数据失败";
    String DB_QUERY_NOT_EXIST = "查询数据不存在";

}