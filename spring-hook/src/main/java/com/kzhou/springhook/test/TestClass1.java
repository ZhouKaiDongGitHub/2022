package com.kzhou.springhook.test;

import org.springframework.stereotype.Service;

/**
 * @Description TestClass1
 * @Author zhoukaidong
 * @Date 2022/2/8 19:14
 **/
@Service
public class TestClass1 {

    private String name;
    private String sex;

    public void call(){
        System.out.println("TestClass1 call");
    }
}
