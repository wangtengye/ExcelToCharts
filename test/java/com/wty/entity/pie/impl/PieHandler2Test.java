package com.wty.entity.pie.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by user on 2017/8/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-entity.xml"})
public class PieHandler2Test {
    @Autowired
    PieHandler2 pieHandler2;

    @Test
    public void testGetJson() throws Exception {
        System.out.println(pieHandler2.getJson());
    }
}