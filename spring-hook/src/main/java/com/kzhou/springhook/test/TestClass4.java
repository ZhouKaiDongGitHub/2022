package com.kzhou.springhook.test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description TestClass4
 * @Author zhoukaidong
 * @Date 2022/2/8 19:14
 **/
public class TestClass4 {

    @Autowired
    private TestClass1 testClass1;

    public void call(){
        System.out.println("TestClass4 call");
    }
}
