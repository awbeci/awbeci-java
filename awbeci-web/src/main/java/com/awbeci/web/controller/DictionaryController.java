package com.awbeci.web.controller;

import com.awbeci.core.bean.Page;
import com.awbeci.web.model.Dictionary;
import com.awbeci.web.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by [Sun Wang] on 2015/10/31.
 */
@Controller
@RequestMapping("admin/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("test")
    @ResponseBody
    public Map<String, Object> test() {
        Page<Dictionary> page = dictionaryService.find(new Page<Dictionary>(), null);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("page", page);
        return result;
    }

}
