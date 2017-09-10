package com.wty.entity.line.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-entity.xml"})
public class LineHandler2Test {
    @Autowired
    LineHandler2 lineHandler2;
    @Test
    public void testGetJson() throws Exception {
        System.out.println(lineHandler2.getJson());
    }
}