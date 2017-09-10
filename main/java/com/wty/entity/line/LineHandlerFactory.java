package com.wty.entity.line;

import com.wty.entity.line.impl.LineHandler1;
import com.wty.entity.line.impl.LineHandler2;
import com.wty.entity.line.impl.LineHandler3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2017/8/15.
 */
@Component
public class LineHandlerFactory {

    @Autowired
    LineHandler1 lineHandler1;
    @Autowired
    LineHandler2 lineHandler2;
    @Autowired
    LineHandler3 lineHandler3;
    public LineHandler getInstance(String id) {
        if ("1".equals(id))
            return lineHandler1;
        else if("2".equals(id))
            return lineHandler2;
        else if("3".equals(id))
            return lineHandler3;
        return null;
    }
}
