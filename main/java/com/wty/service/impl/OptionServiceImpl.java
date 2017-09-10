package com.wty.service.impl;

import com.wty.service.ServiceFactory;
import com.wty.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    ServiceFactory serviceFactory;

    public String getJson(String type, String id) {
        return serviceFactory.getInstance(type).getJsonById(id);
    }
}
