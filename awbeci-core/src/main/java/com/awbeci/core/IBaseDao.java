/**
 * 巨商汇平台 版权所有 Copyright@2014
 */
package com.awbeci.core;

import com.awbeci.core.bean.Page;
import com.github.miemiedev.mybatis.paginator.domain.Order;

import java.io.Serializable;
import java.util.List;

/**
 * [抽象Dao接口]
 *
 * @ProjectName: [gooday-services-shuke]
 * @Author: [Sun Wang]
 * @CreateDate: [2015/10/16 11:46]
 * @Update: [说明本次修改内容] BY [SunWang] [2015/10/16]
 * @Version: [v1.0]
 */
public interface IBaseDao<T, ID extends Serializable> {

    /**
     * 添加（持久化）对象
     *
     * @param object 要添加持久化对象
     * @return 执行成功的记录个数
     */
    Integer insert(T object);

    /**
     * 更新（持久化）对象
     *
     * @param object 要更新的持久化对象
     * @return 执行成功的记录个数
     */
    Integer update(T object);

    /**
     * 删除（持久化）对象
     *
     * @param object 要删除的持久化对象
     * @return 执行成功的记录个数
     */
    Integer delete(T object);

    /**
     * 查询详情
     *
     * @param id 主键ID
     * @return
     */
    T detail(ID id);

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param parameters 查询参数
     * @param orders 排序条件：通过Order.create(sort, dir)方法创建
     * @return
     */
    Page<T> findPage(Page<T> page, Object parameters, Order... orders);

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param parameters 查询参数
     * @return
     */
    Page<T> findPage(Page<T> page, Object parameters);

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    Page<T> findPage(Page<T> page);

    /**
     * 查询列表
     *
     * @param parameters 查询参数
     * @param offset 起始行号
     * @param limit 查询行数
     * @return
     */
    List<T> findList(Object parameters, int offset, int limit);

    /**
     * 查询列表
     *
     * @param parameters 查询参数
     * @return
     */
    List<T> findList(Object parameters);

    /**
     * 查询列表
     *
     * @return
     */
    List<T> findList();

}
