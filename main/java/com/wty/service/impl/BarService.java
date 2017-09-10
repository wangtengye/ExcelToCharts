package com.wty.service.impl;

import com.wty.entity.bar.BarHandlerFactory;
import com.wty.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class BarService implements ChartService {
    @Autowired
    BarHandlerFactory factory;

    public String getJsonById(String id) {
        return factory.getInstance(id).getJson();
    }
}
