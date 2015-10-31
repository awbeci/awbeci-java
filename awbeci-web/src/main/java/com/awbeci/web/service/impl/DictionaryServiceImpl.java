package com.awbeci.web.service.impl;

import com.awbeci.core.bean.Page;
import com.awbeci.core.bean.Params;
import com.awbeci.web.dao.DictionaryDao;
import com.awbeci.web.model.Dictionary;
import com.awbeci.web.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by [Sun Wang] on 2015/10/31.
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    public Page<Dictionary> find(Page<Dictionary> page, Params params) {
        return dictionaryDao.findPage(page, params);
    }
}
