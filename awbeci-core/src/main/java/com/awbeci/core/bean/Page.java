package com.awbeci.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * Created by [Sun Wang] on 2015/10/30.
 */
public class Page<T> implements Serializable {


    private int pageNo = 1;         //页码
    private int pageSize = 20;      //每页数据量
    private long totalCount = 0;     //总数据量
    private List<T> pageData = new ArrayList<T>();   //数据

    /**
     * 无参构造
     */
    public Page() {}

    /**
     * 带参构造
     * @param pageNo 当前页码
     * @param pageSize 每页数据量
     */
    public Page(int pageNo, int pageSize) {
        this.setPageNo(pageNo).setPageSize(pageSize);
    }

    /**
     * 获取总页数
     * @return 返回总页数，总页数最小值为1
     */
    public long getPageCount() {
        long pageCount = (long) Math.ceil(totalCount * 1.0 / pageSize);
        return pageCount > 0 ? pageCount : 1;
    }

    /**
     * 获取起始数据行号
     * @return 当前页面第一条数据在所有数据中的行号
     */
    public int getStartNo() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 是否有上一页
     * @return true当前页不是首页，false当前页是首页
     */
    public boolean hasPrevious() {
        return pageNo > 1;
    }

    /**
     * 是否有下一页
     * @return true当前页不是末页，false当前页是末页
     */
    public boolean hasNext() {
        return pageNo < getPageCount();
    }

    /**
     * 获取当前页码
     * @return
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页码
     * @param pageNo 目标页码，最小值为1
     * @return
     */
    public Page setPageNo(int pageNo) {
        if (pageNo > 0) this.pageNo = pageNo;
        return this;
    }

    /**
     * 获取每页数据量
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页数据量
     * @param pageSize 每页数据量，最小值为1
     * @return
     */
    public Page setPageSize(int pageSize) {
        if (pageSize > 0) this.pageSize = pageSize;
        return this;
    }

    /**
     * 获取总数据量
     * @return 返回总数据量
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总数据量
     * @param totalCount 总数据量，最小值为0
     * @return
     */
    public Page setTotalCount(long totalCount) {
        if(totalCount > 0) this.totalCount = totalCount;
        return this;
    }

    /**
     * 获取当前页面数据列表
     * @return 返回当前页面数据列表
     */
    public List<T> getPageData() {
        return pageData;
    }

    /**
     * 设置当前页面数据
     * @param pageData 当前页面数据
     * @return
     */
    public Page setPageData(List<T> pageData) {
        this.pageData = pageData;
        return this;
    }

}
