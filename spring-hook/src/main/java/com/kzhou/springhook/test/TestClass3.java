package com.kzhou.springhook.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TestClass3
 * @Author zhoukaidong
 * @Date 2022/2/8 19:14
 **/
public class TestClass3 {

    @Autowired
    private TestClass1 testClass1;

    public void call(){
        System.out.println("TestClass3 call");
    }
}
