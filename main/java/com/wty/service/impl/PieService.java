package com.wty.service.impl;

import com.wty.entity.pie.PieHandlerFactory;
import com.wty.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class PieService implements ChartService {
    @Autowired
    PieHandlerFactory factory;

    public String getJsonById(String id) {
        return factory.getInstance(id).getJson();
    }
}
