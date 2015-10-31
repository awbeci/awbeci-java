package com.awbeci.web.service;

import com.awbeci.core.bean.Page;
import com.awbeci.core.bean.Params;
import com.awbeci.web.model.Dictionary;

import java.util.List;

/**
 * 数据字典Service api
 * Created by [Sun Wang] on 2015/10/31.
 */
public interface DictionaryService {

    Page<Dictionary> find(Page<Dictionary> page, Params params);

}
