package com.wty.entity.pie;

import com.wty.entity.pie.impl.PieHandler1;
import com.wty.entity.pie.impl.PieHandler2;
import com.wty.entity.pie.impl.PieHandler3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2017/8/15.
 */
@Component
public class PieHandlerFactory {

    @Autowired
    PieHandler1 pieHandler1;
    @Autowired
    PieHandler2 pieHandler2;
    @Autowired
    PieHandler3 pieHandler3;
    public PieHandler getInstance(String id) {
        if ("1".equals(id))
            return pieHandler1;
        else if("2".equals(id))
            return pieHandler2;
        else if("3".equals(id))
            return pieHandler3;
        return null;
    }
}
