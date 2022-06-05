package com.kzhou.springhook.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TestClass2
 * @Author zhoukaidong
 * @Date 2022/2/8 19:14
 **/
@Service
public class TestClass2 {

    @Autowired
    private TestClass1 testClass1;

    public void call(){
        testClass1.call();
        System.out.println("TestClass2 call");
    }
}
