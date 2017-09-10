package com.wty.entity.bar.impl;

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
public class BarHandler2Test {


    @Autowired
    BarHandler2 barHandler2;

    @Test
    public void testGetJson() throws Exception {
        System.out.println(barHandler2.getJson());
    }
}
