package com.wty.service;

import com.wty.service.impl.BarService;
import com.wty.service.impl.LineService;
import com.wty.service.impl.PieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class ServiceFactory {
    @Autowired
    BarService barService;
    @Autowired
    LineService lineService;
    @Autowired
    PieService pieService;
    public ChartService getInstance(String type) {
        if ("bar".equals(type))
            return barService;
        else if("line".equals(type))
            return lineService;
        else if("pie".equals(type))
            return pieService;
        return null;
    }
}
