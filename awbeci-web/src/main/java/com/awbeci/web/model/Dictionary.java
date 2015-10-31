package com.awbeci.web.model;

/**
 * 数据字典
 * Created by [Sun Wang] on 2015/10/31.
 */
public class Dictionary {

    private String id;          //主键
    private String dictText;    //字典文本
    private Integer dictValue;  //字典值
    private String dictCode;    //字典类型标识
    private String dictDesc;    //字典描述
    private Integer sort;       //排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictText() {
        return dictText;
    }

    public void setDictText(String dictText) {
        this.dictText = dictText;
    }

    public Integer getDictValue() {
        return dictValue;
    }

    public void setDictValue(Integer dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
