package com.wty.entity.bar;

import com.wty.entity.bar.impl.BarHandler1;
import com.wty.entity.bar.impl.BarHandler2;
import com.wty.entity.bar.impl.BarHandler3;
import com.wty.entity.bar.impl.BarHandler4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2017/8/2.
 */
@Component
public class BarHandlerFactory {
    @Autowired
    BarHandler1 barHandler1;
    @Autowired
    BarHandler2 barHandler2;
    @Autowired
    BarHandler3 barHandler3;
    @Autowired
    BarHandler4 barHandler4;

    public BarHandler getInstance(String id) {
        if ("1".equals(id))
            return barHandler1;
        else if("2".equals(id))
            return barHandler2;
        else if("3".equals(id))
            return barHandler3;
        else if("4".equals(id))
            return barHandler4;
        return null;
    }
}
