package com.wty.service.impl;

import com.wty.entity.line.LineHandlerFactory;
import com.wty.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class LineService implements ChartService {
    @Autowired
    LineHandlerFactory factory;

    public String getJsonById(String id) {
        return factory.getInstance(id).getJson();
    }
}
